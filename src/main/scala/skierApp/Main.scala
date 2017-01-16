package skierApp


import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import com.typesafe.scalalogging.StrictLogging
import skierApp.api.{Controller, StatisticsController}
import skierApp.statistics.StatisticsActor
import skierApp.utils.{ActorImplicits, HttpsConfig, Settings}

import scala.util.{Failure, Success}

object Main extends StrictLogging with ActorImplicits with HttpsConfig {

  def main(args: Array[String]) = {

    val statsActor = actorSystem.actorOf(StatisticsActor.props, "statisticsActor")

    val controllers: List[Controller] = List(
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
}
