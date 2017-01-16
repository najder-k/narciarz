package skierApp.api

import akka.http.scaladsl.server.Route
import akka.util.Timeout
import de.heikoseeberger.akkahttpjson4s.Json4sSupport
import org.json4s.{Formats, jackson}

import scala.concurrent.duration._
import scala.language.postfixOps

trait Controller extends Json4sSupport {

  implicit val serialization = jackson.Serialization
  implicit val formats: Formats

  implicit val timeout: Timeout = 5 seconds

  def endpoints: Route

  val userId = """[a-zA-Z0-9_-]+""".r
}

