name := "narciarz"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies ++= {
  val AKKA_VERSION   = "2.4.14"
  Seq(
    "com.typesafe" % "config" % "1.3.1",
    "com.typesafe.akka" %% "akka-actor" % AKKA_VERSION,
    "com.typesafe.akka" %% "akka-slf4j" % AKKA_VERSION,
    "com.typesafe.akka" %% "akka-http-core" % "10.0.0",
    "com.typesafe.akka" %% "akka-persistence" % AKKA_VERSION,
    "com.typesafe.akka" %% "akka-persistence-cassandra" % "0.22",
    "com.github.dnvriend" %% "akka-persistence-inmemory" % "1.3.17",
    "de.heikoseeberger" %% "akka-http-json4s" % "1.11.0",
    "org.json4s"        %% "json4s-jackson" % "3.5.0",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",
    "ch.qos.logback" % "logback-classic" % "1.1.7",
    "org.scala-lang" % "scala-reflect" % "2.12.1"
  )
}


