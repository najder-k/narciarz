package skierApp.serializers

import org.json4s.CustomSerializer
import org.json4s.JsonAST.{JInt, JObject, JValue}
import skierApp.statistics.Parameters.AltitudeParameters


object AltitudeSerializer extends CustomSerializer[AltitudeParameters](implicit format => ( {
  case m: JValue =>

    val maxAltitude = (m \ "maxAltitude").extract[Int]
    val avgAltitude = (m \ "avgAltitude").extract[Int]
    val minAltitude = (m \ "minAltitude").extract[Int]
    AltitudeParameters(maxAltitude, avgAltitude, minAltitude)
}, {
  case AltitudeParameters(maxAltitude, avgAltitude, minAltitude) =>
    JObject(
      "maxAltitude" -> JInt(maxAltitude),
      "avgAltitude" -> JInt(avgAltitude),
      "minAltitude" -> JInt(minAltitude)
    )
}))