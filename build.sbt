name := "narciarz"

version := "1.0"

scalaVersion := "2.11.8"

val akkaVersion   = "2.4.14"
crossScalaVersions in ThisBuild := Seq("2.10.4", "2.11.6")

resolvers += "krasserm at bintray" at "http://dl.bintray.com/krasserm/maven"
logLevel := Level.Info
libraryDependencies ++= {
  Seq(
    "com.typesafe" % "config" % "1.3.1",
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "com.typesafe.akka" %% "akka-http-core" % "10.0.0",
    "com.typesafe.akka" %% "akka-persistence" % akkaVersion,
    "com.typesafe.akka" %% "akka-persistence-cassandra" % "0.22",
    "com.github.dnvriend" %% "akka-persistence-inmemory" % "1.3.17",
    "de.heikoseeberger" %% "akka-http-json4s" % "1.11.0",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",
    "ch.qos.logback" % "logback-classic" % "1.1.7",
    "org.scala-lang" % "scala-reflect" % "2.11.8",
    "org.apache.spark" %% "spark-core" % "1.6.1",
    "org.apache.spark" % "spark-streaming_2.11" % "1.6.1",
    "org.apache.spark"     %% "spark-sql"                  % "1.6.1",
    "com.datastax.spark"   %% "spark-cassandra-connector"  % "1.6.4",
    "com.github.krasserm" %% "akka-analytics-cassandra" % "0.3.1"
  )
}
