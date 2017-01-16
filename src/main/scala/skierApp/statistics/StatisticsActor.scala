package skierApp.statistics

import akka.actor.{ActorLogging, Props}
import akka.analytics.cassandra._
import akka.persistence.PersistentActor
import skierApp.statistics.utils._

class StatisticsActor extends PersistentActor with ActorLogging with SparkConfig {

  val entityId = self.path.name
  var highestAltitudeCache: Map[String, Int] = Map.empty

  override def persistenceId = StatisticsActor.persistenceId(entityId)

  override def receiveRecover: Receive = {

    case HighestAltitudeCalculated(ownerId, altitude) =>
      highestAltitudeCache += ownerId -> altitude

  }

  override def receiveCommand: Receive = {
    case stats: AddStatistics =>

      persist(stats.event) { trigger =>
        log.info("Saving statistics to database")
        sender() ! trigger
      }

    case ev@CalculateHighestAltitude(ownerId) =>
      sender() ! "Highest Altitude for user " + ownerId + " will be calculated"

      val sc = sparkConfig("Highest Altitude")
      val events = sc.eventTable("skier", "messages").cache()

      val highestAltitude = events.collect()
        .flatMap {
          case (_, e: StatisticsAdded) => Some(e)
          case _ => None
        }.filter(_.ownerId == ownerId)
        .map(_.stats.altitude.maxAltitude)
        .max

      sc.stop()

      persist(HighestAltitudeCalculated(ownerId, highestAltitude)) { trigger =>
        log.info("Highest Altitude for user {} is {}", ownerId, highestAltitude)
        highestAltitudeCache += ownerId -> highestAltitude
      }

    case GetHighestAltitude(ownerId) =>
      sender() ! highestAltitudeCache
        .getOrElse(ownerId, NoStatisticsAvailable("Requested statistics were not calculated for this user"))
  }
}

object StatisticsActor {
  def props = Props(new StatisticsActor)

  def persistenceId(entityId: String): String = "instance_stats_" + entityId
}