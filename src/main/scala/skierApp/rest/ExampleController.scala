package skierApp.rest

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import org.json4s.DefaultFormats
import skierApp.services.ExampleService

import scala.concurrent.Future
import scala.util.{Failure, Success}

class ExampleController extends Controller {

  implicit val formats = DefaultFormats

  def endpoints: Route =
    path("counter") {
      (post & entity(as[CounterChange])) { case CounterChange(value) =>
        completeRequest(ExampleService.changeCounter(value))
      } ~
        get {
          completeRequest(ExampleService.getCounter)
        }
    }

  private def completeRequest(request: Future[Int]) =
    onComplete(request) {
      case Success(value) =>
        complete(StatusCodes.OK -> ("counterValue" -> value))
      case Failure(ex) =>
        complete(StatusCodes.InternalServerError -> ("unexpectedError" -> ex))
    }

}

case class CounterChange(changeBy: Int)