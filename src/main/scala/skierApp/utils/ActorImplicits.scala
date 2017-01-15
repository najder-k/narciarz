package skierApp.utils

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.util.Timeout

import scala.concurrent.duration._
import scala.language.postfixOps

trait ActorImplicits {

  implicit val actorSystem = ActorSystem("skierApp")
  implicit val materializer = ActorMaterializer()
  implicit val dispatcher = actorSystem.dispatcher
  implicit val timeout: Timeout = 5 seconds

}
