package skierApp.statistics.utils.serializers

import org.json4s.JsonAST.{JInt, JObject, JString, JValue}
import org.json4s.{CustomSerializer, Extraction}
import skierApp.statistics._
import skierApp.statistics.utils.{AltitudeParameters, NarciarzData, SlopeParameters, SpeedParameters}

object StatisticsSerializer extends CustomSerializer[NarciarzStatistics](implicit format => ( {
  case m: JValue =>

    val pathPrefix = m \ "narciarzStatistics"

    val timeStamp = (pathPrefix \ "timeStamp").extract[String]
    val skiingType = (pathPrefix \ "skiingType").extract[String]
    val speed = (pathPrefix \ "speed").extract[SpeedParameters]
    val altitude = (pathPrefix \ "altitude").extract[AltitudeParameters]
    val slope = (pathPrefix \ "slope").extract[SlopeParameters]
    val totalDistance = (pathPrefix \ "totalDistance").extract[Int]
    val caloriesRate = (pathPrefix \ "caloriesRate").extract[Int]
    val runsNumber = (pathPrefix \ "runsNumber").extract[Int]
    val data = (pathPrefix \ "data").extract[List[NarciarzData]]
    NarciarzStatistics(timeStamp, skiingType, speed, altitude, slope, totalDistance, caloriesRate, runsNumber, data)
}, {
  case NarciarzStatistics(timeStamp, skiingType, speed, altitude, slope, totalDistance, caloriesRate, runsNumber, data) =>
    JObject(
      "timeStamp" -> JString(timeStamp),
      "skiingType" -> JString(skiingType),
      "speed" -> Extraction.decompose(speed),
      "altitude" -> Extraction.decompose(altitude),
      "slope" -> Extraction.decompose(slope),
      "totalDistance" -> JInt(totalDistance),
      "caloriesRate" -> JInt(caloriesRate),
      "runsNumber" -> JInt(runsNumber),
      "data" -> Extraction.decompose(data)
    )
}))