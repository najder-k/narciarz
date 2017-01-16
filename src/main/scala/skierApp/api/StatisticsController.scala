package skierApp.api

import akka.actor.ActorRef
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.pattern.ask
import org.json4s.DefaultFormats
import skierApp.statistics.utils.serializers._
import skierApp.statistics._
import skierApp.statistics.utils.{AddStatistics, CalculateHighestAltitude, GetHighestAltitude, NoStatisticsAvailable}

import scala.concurrent.Future
import scala.util.{Failure, Success}


class StatisticsController(statsActor: ActorRef) extends Controller {

  implicit val formats = DefaultFormats + SlopeSerializer + AltitudeSerializer +
    NarciarzDataSerializer + SpeedSerializer + StatisticsSerializer

  def endpoints: Route =
    path("users" / userId / "stats") { userId =>
      (post & entity(as[NarciarzStatistics])) { stats =>
        val result = statsActor ? AddStatistics(userId, stats)

        handleRequest(result)
      }
    } ~ path("users" / userId / "altitude") { userId =>
      post {
        val result = statsActor ? CalculateHighestAltitude(userId)

        handleRequest(result)
      } ~ get {
        val result = statsActor ? GetHighestAltitude(userId)

        handleRequest(result)
      }
    }

  private def handleRequest(result: Future[_]): Route =
    onComplete(result) {
      case Success(res: AnyRef) => complete(StatusCodes.Accepted -> res)
      case Failure(ex: NoStatisticsAvailable) => complete(StatusCodes.BadRequest -> ex)
      case Failure(ex: Any) => complete(StatusCodes.InternalServerError -> ex)
    }
}
