package skierApp.statistics.utils

case class SpeedParameters(maxSpeed: Double, avgSpeed: Double, minSpeed: Double)

case class AltitudeParameters(maxAltitude: Int, avgAltitude: Int, minAltitude: Int)

case class SlopeParameters(avgSlope: Int, maxSlope: Int)

case class NarciarzData(altitude: Double, longtitude: Double, latitude: Double,
                        speed: Double, course: Double, timeStamp: String,
                        motion: String)