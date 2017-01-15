package skierApp.statistics

import akka.actor.{ActorLogging, Props}
import akka.persistence.PersistentActor

class StatisticsActor extends PersistentActor with ActorLogging {

  val entityId = self.path.name
  override def persistenceId = StatisticsActor.persistenceId(entityId)

  override def receiveRecover: Receive = {
    case stats: StatisticsAdded =>

  }

  override def receiveCommand: Receive = {
    case stats: AddStatistics =>

      persist(stats.event) { trigger =>
        log.info("Saving statistics to database")
        sender() ! trigger
      }
  }

}

object StatisticsActor {
  def props = Props(new StatisticsActor)

  def persistenceId(entityId: String): String = "instance_stats_" + entityId
}