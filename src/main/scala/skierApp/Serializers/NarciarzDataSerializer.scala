package skierApp.serializers

import org.json4s.CustomSerializer
import org.json4s.JsonAST._
import skierApp.statistics.Parameters.NarciarzData

object NarciarzDataSerializer extends CustomSerializer[NarciarzData](implicit format => ( {
  case m: JValue =>

    val altitude = (m \ "altitude").extract[Double]
    val longtitude = (m \ "longtitude").extract[Double]
    val latitude = (m \ "latitude").extract[Double]
    val speed = (m \ "speed").extract[Double]
    val course = (m \ "course").extract[Double]
    val timeStamp = (m \ "timeStamp").extract[String]
    val motion = (m \ "motion").extract[String]

    NarciarzData(altitude, longtitude, latitude, speed, course, timeStamp, motion)
}, {
  case NarciarzData(altitude, longtitude, latitude, speed, course, timeStamp, motion) =>
    JObject(
      "altitude" -> JDouble(altitude),
      "longtitude" -> JDouble(longtitude),
      "latitude" -> JDouble(latitude),
      "speed" -> JDouble(speed),
      "course" -> JDouble(course),
      "timeStamp" -> JString(timeStamp),
      "motion" -> JString(motion)
    )
}))