name := "narciarz"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= {
  val AKKA_VERSION   = "2.4.8"
  Seq(
    "com.typesafe" % "config" % "1.3.0",
    "com.typesafe.akka" %% "akka-actor" % AKKA_VERSION,
    "com.typesafe.akka" %% "akka-slf4j" % AKKA_VERSION,
    "com.typesafe.akka" %% "akka-http-core" % AKKA_VERSION,
    "com.typesafe.akka" %% "akka-http-experimental" % AKKA_VERSION,
    "com.typesafe.akka" %% "akka-persistence" % AKKA_VERSION,
    "com.typesafe.akka" %% "akka-persistence-cassandra" % "0.17",
    "com.github.dnvriend" %% "akka-persistence-inmemory" % "1.2.2",
    "de.heikoseeberger" %% "akka-http-json4s" % "1.6.0" exclude("org.json4s", "json4s-core_2.11"),
    "org.json4s"        %% "json4s-jackson" % "3.2.11",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.4.0",
    "ch.qos.logback" % "logback-classic" % "1.1.2"
  )
}


