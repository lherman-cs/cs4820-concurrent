// create one Party and 10 ParticipantGenerators, half with the role 
//  “superhero” and half with the role “sidekick”. Instrument the code
//   with print statements to indicate what is happening.

import akka.actor.{ ActorSystem, Actor, ActorRef, Props, PoisonPill }
import language.postfixOps
import scala.concurrent.duration._
import scala.collection.mutable.HashSet

abstract class Message

case class Enter(role: String) extends Message
case class Exit(role: String, participantGenerator: ActorRef) extends Message
case object FailExit extends Message
case object SucceedExit extends Message



class Party extends Actor {
  var superheros = HashSet.empty[String]
  var sidekicks = HashSet.empty[String]
  var exitable = HashSet.empty[String]
  val SuperheroPattern = "(superhero-[0-9]+)".r
  val SidekickPattern = "(sidekick-[0-9]+)".r

  def receive = {
    case Enter(SuperheroPattern(name)) =>
      superheros += name
      println(s"${name} entered the party")

    case Enter(SidekickPattern(name)) =>      
      sidekicks += name
      println(s"${name} entered the party")

    case Exit(SuperheroPattern(name), participantGenerator) =>

      if (exitable(name)){
        exitable -= name
        sender() ! SucceedExit
      } else if (sidekicks.size >= 1){
        superheros -= name
        var sidekick = sidekicks.head
        sidekicks -= sidekick
        exitable += sidekick
        println(s"${name} exits with ${sidekick}")
        sender() ! SucceedExit
      } else {
        println(s"${name} fails to exit")
        sender() ! FailExit
      }

    case Exit(SidekickPattern(name), participantGenerator) =>
      if(exitable(name)){
        exitable -= name
        sender() ! SucceedExit
      } else if (superheros.size >= 1){
        sidekicks -= name
        var superhero = superheros.head
        superheros -= superhero
        exitable += superhero
        println(s"${name} exits with ${superhero}")
        sender() ! SucceedExit
      } else {
        println(s"${name} fails to exit")
        sender() ! FailExit
      }

  }
}

class ParticipantGenerator(party: ActorRef) extends Actor {
  val role = self.path.name
  val enter = Enter(role)
  val exit = Exit(role, self)

  def receive = {
    case SucceedExit =>
      party ! enter
      party ! exit
    case FailExit => 
      party ! exit
    case "start" => 
      println(s"Create ${role} Generator")
      party ! enter
      party ! exit
  }
}

object PartyMatching extends App {
    import system.dispatcher

    val system = ActorSystem("PartyMatching")

    val party = system.actorOf(Props[Party], "party")

    for (x <- 1 to 5){
      system.actorOf(Props(classOf[ParticipantGenerator], party), s"sidekick-${x}") ! "start" 
      system.actorOf(Props(classOf[ParticipantGenerator], party), s"superhero-${x}") ! "start"
    }
}