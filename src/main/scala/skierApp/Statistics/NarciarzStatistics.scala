package skierApp.statistics

import skierApp.statistics.Parameters.{AltitudeParameters, NarciarzData, SlopeParameters, SpeedParameters}

case class NarciarzStatistics(timeStamp: String, skiingType: String, speed: SpeedParameters, altitude: AltitudeParameters,
                              slope: SlopeParameters, totalDistance: Int, caloriesRate: Int, runsNumber: Int,
                              data: List[NarciarzData])


trait Command

trait Event extends Serializable

case class AddStatistics(ownerId: String, stats: NarciarzStatistics) extends Command {
  def event = StatisticsAdded(ownerId, stats)
}
case class StatisticsAdded(ownerId: String, stats: NarciarzStatistics) extends Event