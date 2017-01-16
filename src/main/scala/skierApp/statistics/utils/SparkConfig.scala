package skierApp.statistics.utils

import org.apache.spark.{SparkConf, SparkContext}


trait SparkConfig {

  def sparkConfig(appName: String): SparkContext =
    new SparkContext(
      new SparkConf()
        .setAppName(appName)
        .setMaster("local[4]")
        .set("spark.cassandra.connection.host", "127.0.0.1"))
}
