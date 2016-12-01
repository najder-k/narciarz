package skierApp

import akka.http.scaladsl.Http
import com.typesafe.scalalogging.StrictLogging
import skierApp.actors.ActorImplicits._
import skierApp.rest.ExampleController
import skierApp.utils.Settings

import scala.util.{Failure, Success}

object Main extends App with StrictLogging {

  Http().bindAndHandle(handler = ExampleController.endpoints, interface = Settings.host, port = Settings.port)
    .onComplete {
      case Success(b) =>
        logger.info("Successfully bound to {}", b.localAddress)
      case Failure(ex) =>
        logger.error("Couldn't bind, because of {}", ex.getMessage)
    }

}
