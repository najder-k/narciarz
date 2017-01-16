package skierApp.statistics.utils

import skierApp.statistics.NarciarzStatistics

trait Command

trait Event extends Serializable

case class AddStatistics(ownerId: String, stats: NarciarzStatistics) extends Command {
  def event = StatisticsAdded(ownerId, stats)
}
case class StatisticsAdded(ownerId: String, stats: NarciarzStatistics) extends Event

case class CalculateHighestAltitude(ownerId: String) extends Command

case class HighestAltitudeCalculated(ownerId: String, altitude: Int) extends Event

case class GetHighestAltitude(ownerId: String)