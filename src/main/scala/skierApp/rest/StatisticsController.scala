package skierApp.rest

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import org.json4s.DefaultFormats
import skierApp.serializers._
import skierApp.statistics.NarciarzStatistics


class StatisticsController extends Controller {

  implicit val formats = DefaultFormats + SlopeSerializer + AltitudeSerializer +
    NarciarzDataSerializer + SpeedSerializer + StatisticsSerializer

  def endpoints: Route =

    path("users" / userId / "stats") { userId =>
      (post & entity(as[NarciarzStatistics])) { stats =>
        println(stats)
        complete(StatusCodes.OK)
      }
    }
}
