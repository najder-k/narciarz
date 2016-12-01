package skierApp.services

import akka.actor.ActorRef
import akka.pattern.ask
import skierApp.actors.ActorImplicits.timeout
import skierApp.actors.{ActorHolder, ChangeCounter, GetCounter}

import scala.concurrent.Future

object ExampleService {

  val exampleActor: ActorRef = ActorHolder.exampleActor

  def changeCounter(value: Int): Future[Int] = {
    val anwser = exampleActor ? ChangeCounter(value)
    anwser.mapTo[Int]
  }

  def getCounter: Future[Int] = {
    val anwser = exampleActor ? GetCounter
    anwser.mapTo[Int]
  }

}
