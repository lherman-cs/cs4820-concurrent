import akka.actor.{ ActorSystem, Actor, ActorRef, Props, PoisonPill }
import language.postfixOps
import scala.concurrent.duration._

case object Enter

class Garden extends Actor {
  var maxOccupancy = 100
  var counter = 0
  var gatesActive = 2

  def receive = {
    case Enter =>
      if(counter >= maxOccupancy){
        sender() ! PoisonPill
        gatesActive -= 1
        if(gatesActive == 0) self ! PoisonPill
      }
      else {
        counter += 1
        println(s"${self.path.name} contains $counter people")
      }
  }
}

class Gate(garden: ActorRef) extends Actor {
  var counter = 0

  def receive = {
    case Enter =>
      counter += 1
      println(s"${self.path.name} has served $counter people")
      garden ! Enter
  }
}

object OrnamentalGarden extends App {
    import system.dispatcher

    val system = ActorSystem("OrnamentalGarden")

    val garden = system.actorOf(Props[Garden], "garden")
    val east = system.actorOf(Props(classOf[Gate], garden), "east")
    val west = system.actorOf(Props(classOf[Gate], garden), "west")

    system.scheduler.schedule(0 millis, 500 millis) {
      east ! Enter
    }

    system.scheduler.schedule(0 millis, 100 millis) {
      west ! Enter
    }
}
