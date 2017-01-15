package skierApp

import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import com.typesafe.scalalogging.StrictLogging
import skierApp.rest.{Controller, StatisticsController}
import skierApp.statistics.StatisticsActor
import skierApp.utils.{ActorImplicits, HttpsConfig, Settings}

import scala.util.{Failure, Success}

object Main extends App with StrictLogging with ActorImplicits with HttpsConfig {

  val statsActor = actorSystem.actorOf(StatisticsActor.props, "statisticsActor")


  val controllers: List[Controller] = List (
    new StatisticsController(statsActor)
  )

  val routes = controllers.map(_.endpoints).reduce(_ ~ _)

  Http().bindAndHandle(handler = routes, interface = Settings.host, port = Settings.port, connectionContext = https)
    .onComplete {
      case Success(b) =>
        logger.info("Successfully bound to {}", b.localAddress)
      case Failure(ex) =>
        logger.error("Couldn't bind, because of {}", ex.getMessage)
    }

}
