import akka.actor.{ ActorSystem, Actor, ActorRef, Props, PoisonPill }
import language.postfixOps
import scala.concurrent.duration._

// we define two types of messages, ping and pong
case object Ping
case object Pong

// we define one type of actor, a pinger 
class Pinger extends Actor {
  // define a variable, countdown
  var countDown = 100
  // by extending the actor class, we must have a funciton, recieve
  //   this is how we accept messages
  def receive = {
    // if we recieve a pong message
    case Pong => 
      // logging
      println(s"${self.path} received pong, count down $countDown")
      // if we should keep going
      if (countDown > 0) {
        // decrement our counter
        countDown -= 1
        // send a ping message to whomever send us the pong message
        sender() ! Ping
      } 
      // otherwise, lets terminate the program
      else {
        // kill the actor who sent me the most recent pong message
        sender() ! PoisonPill
        // kill myself
        self ! PoisonPill
      }
  }
}

// pinger is a perameter accepted by ponger
//   ponger needs this so that it can send messages to pinger
class Ponger(pinger: ActorRef) extends Actor {
  def receive = {
    // if we got a ping message
    case Ping => 
      println(s"${self.path} received ping")
      // send a pong message to the actor who was passed in, pinger
      pinger ! Pong
  }
}


object PingPong extends App {
    // create an actor system, kind of like a container for the actors, imported
    val system = ActorSystem("pingpong")
    // create a pinger actor, defined above
    val pinger = system.actorOf(Props[Pinger], "pinger")
    // create a ponger actor, defined above
    val ponger = system.actorOf(Props(classOf[Ponger], pinger), "ponger")
    // every 500 millis, send a ping message to ponger
    import system.dispatcher
    system.scheduler.scheduleOnce(500 millis) {
      ponger ! Ping
    }
}
