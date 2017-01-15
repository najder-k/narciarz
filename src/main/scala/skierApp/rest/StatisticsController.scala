package skierApp.rest

import akka.actor.ActorRef
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.pattern.ask
import org.json4s.DefaultFormats
import skierApp.serializers._
import skierApp.statistics.{AddStatistics, NarciarzStatistics, StatisticsAdded}

import scala.util.{Failure, Success}


class StatisticsController(statsActor: ActorRef) extends Controller {

  implicit val formats = DefaultFormats + SlopeSerializer + AltitudeSerializer +
    NarciarzDataSerializer + SpeedSerializer + StatisticsSerializer

  def endpoints: Route =

    path("users" / userId / "stats") { userId =>
      (post & entity(as[NarciarzStatistics])) { stats =>


        val result = statsActor ? AddStatistics(userId, stats)

        onComplete(result) {
          case Success(res: StatisticsAdded) => complete(StatusCodes.Accepted -> res)
          case Failure(ex: Any) => complete(StatusCodes.InternalServerError -> ex)
        }
      }
    }
}
