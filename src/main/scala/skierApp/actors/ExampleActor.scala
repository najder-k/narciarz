package skierApp.actors

import akka.actor.{ActorLogging, Props}
import akka.persistence.PersistentActor

object ExampleActor {
  def props = Props(new ExampleActor)
}

case class ChangeCounter(value: Int)

case class CounterChanged(value: Int)

case object GetCounter

class ExampleActor extends PersistentActor with ActorLogging {

  var counter = 0

  override def persistenceId: String = "example"

  override def receiveRecover: Receive = {
    case CounterChanged(value) =>
      counter += value
  }

  override def receiveCommand: Receive = {
    case ChangeCounter(value) =>
      persist(CounterChanged(value)) { _ =>
        log.info("Increased counter value by {}", value)
        counter += value
        sender() ! counter
      }
    case GetCounter =>
      sender() ! counter
  }

}
