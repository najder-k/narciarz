package skierApp.rest

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.{Directives, Route}
import de.heikoseeberger.akkahttpjson4s.Json4sSupport
import org.json4s.{DefaultFormats, jackson}
import skierApp.services.ExampleService

import scala.concurrent.Future
import scala.util.{Failure, Success}

object ExampleController extends Directives with Json4sSupport {

  implicit val serialization = jackson.Serialization
  implicit val formats = DefaultFormats

  val endpoints: Route =
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