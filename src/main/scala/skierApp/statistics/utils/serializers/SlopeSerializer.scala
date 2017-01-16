package skierApp.statistics.utils.serializers

import org.json4s.CustomSerializer
import org.json4s.JsonAST.{JInt, JObject, JValue}
import skierApp.statistics.utils.SlopeParameters

object SlopeSerializer extends CustomSerializer[SlopeParameters](implicit format => ( {
  case m: JValue =>

    val avgSlope = (m \ "avgSlope").extract[Int]
    val maxSlope = (m \ "maxSlope").extract[Int]
    SlopeParameters(avgSlope, maxSlope)
}, {
  case SlopeParameters(avgSlope, maxSlope) =>
    JObject(
      "avgSlope" -> JInt(avgSlope),
      "maxSlope" -> JInt(maxSlope)
    )
}))
