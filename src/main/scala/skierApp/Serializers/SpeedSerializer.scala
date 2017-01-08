package skierApp.serializers

import org.json4s.CustomSerializer
import org.json4s.JsonAST.{JDouble, JObject, JValue}
import skierApp.statistics.Parameters.SpeedParameters

object SpeedSerializer extends CustomSerializer[SpeedParameters](implicit format => ( {
  case m: JValue =>

    val maxSpeed = (m \ "maxSpeed").extract[Double]
    val avgSpeed = (m \ "avgSpeed").extract[Double]
    val minSpeed = (m \ "minSpeed").extract[Double]
    SpeedParameters(maxSpeed, avgSpeed, minSpeed)
}, {
  case SpeedParameters(maxSpeed, avgSpeed, minSpeed) =>
    JObject(
      "maxSpeed" -> JDouble(maxSpeed),
      "avgSpeed" -> JDouble(avgSpeed),
      "minSpeed" -> JDouble(minSpeed)
    )
}))
