package skierApp.actors

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.util.Timeout

import scala.concurrent.duration._
import scala.language.postfixOps

object ActorImplicits {

  implicit val actorSystem = ActorSystem("skierApp")
  implicit val actorMaterializer = ActorMaterializer()
  implicit val dispatcher = actorSystem.dispatcher
  implicit val timeout: Timeout = 5 seconds

}
