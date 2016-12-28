package skierApp.utils

import com.typesafe.config.ConfigFactory

object Settings {
  val config = ConfigFactory.load()

  lazy val host = config.getString("skier.host")
  lazy val port = config.getInt("skier.port")
  lazy val password = config.getString("skier.password")
}
