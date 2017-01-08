package skierApp.serializers

import org.json4s.JsonAST.{JInt, JObject, JString, JValue}
import org.json4s.{CustomSerializer, Extraction}
import skierApp.statistics.Parameters.{AltitudeParameters, NarciarzData, SlopeParameters, SpeedParameters}
import skierApp.statistics._

object StatisticsSerializer extends CustomSerializer[NarciarzStatistics](implicit format => ( {
  case m: JValue =>

    val timeStamp = (m \ "timeStamp").extract[String]
    val skiingType = (m \ "skiingType").extract[Int]
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
      "skiingType" -> JInt(skiingType),
      "speed" -> Extraction.decompose(speed),
      "altitude" -> Extraction.decompose(altitude),
      "slope" -> Extraction.decompose(slope),
      "totalDistance" -> JInt(totalDistance),
      "caloriesRate" -> JInt(caloriesRate),
      "runsNumber" -> JInt(runsNumber),
      "data" -> Extraction.decompose(data)
    )
}))