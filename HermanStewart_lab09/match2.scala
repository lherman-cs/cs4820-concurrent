// create one Party and 10 ParticipantGenerators, half with the role 
//  “superhero” and half with the role “sidekick”. Instrument the code
//   with print statements to indicate what is happening.

import akka.actor.{ ActorSystem, Actor, ActorRef, Props, PoisonPill }
import language.postfixOps
import scala.concurrent.duration._
import scala.collection.mutable.HashSet

abstract class Message

case class Arrive(role: String, participantGenerator: ActorRef) extends Message
case object Leave extends Message


class Party extends Actor {
  var superheros = HashSet.empty[ActorRef]
  var sidekicks = HashSet.empty[ActorRef]
  val SuperheroPattern = "(superhero-[0-9]+)".r
  val SidekickPattern = "(sidekick-[0-9]+)".r

  def receive = {
    case Arrive(SuperheroPattern(name), _) =>
      println(s"${name} entered the party")
      if (sidekicks.size >= 1){
          sender() ! Leave
          var sidekick = sidekicks.head
          sidekicks -= sidekick
          println(s"${name} exits with ${sidekick.path.name}")
          sidekick ! Leave
      }else {
          superheros += sender()
      }

    case Arrive(SidekickPattern(name), _) =>
      println(s"${name} entered the party")
      if (superheros.size >= 1){
          sender() ! Leave
          var superhero = superheros.head
          superheros -= superhero
          println(s"${name} exits with ${superhero.path.name}")
          superhero ! Leave
      }else {
          sidekicks += sender()
      }

  }
}

class ParticipantGenerator(party: ActorRef) extends Actor {
  val role = self.path.name
  val arrive = Arrive(role, self)

  def receive = {
    case Leave =>
        party ! arrive
      
    case "start" => 
      println(s"Create ${role} Generator")
      party ! arrive
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