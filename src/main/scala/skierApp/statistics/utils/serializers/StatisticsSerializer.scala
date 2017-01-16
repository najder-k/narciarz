package skierApp.statistics.utils.serializers

import org.json4s.JsonAST.{JInt, JObject, JString, JValue}
import org.json4s.{CustomSerializer, Extraction}
import skierApp.statistics._
import skierApp.statistics.utils.{AltitudeParameters, NarciarzData, SlopeParameters, SpeedParameters}

object StatisticsSerializer extends CustomSerializer[NarciarzStatistics](implicit format => ( {
  case m: JValue =>

    val timeStamp = (m \ "timeStamp").extract[String]
    val skiingType = (m \ "skiingType").extract[String]
    val speed = (m \ "speed").extract[SpeedParameters]
    val altitude = (m \ "altitude").extract[AltitudeParameters]
    val slope = (m \ "slope").extract[SlopeParameters]
    val totalDistance = (m \ "totalDistance").extract[Int]
    val caloriesRate = (m \ "caloriesRate").extract[Int]
    val runsNumber = (m \ "runsNumber").extract[Int]
    val data = (m \ "data").extract[List[NarciarzData]]
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