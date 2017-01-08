package skierApp

import java.io.InputStream
import java.security.{KeyStore, SecureRandom}
import javax.net.ssl.{KeyManagerFactory, SSLContext, TrustManagerFactory}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.{ConnectionContext, Http, HttpsConnectionContext}
import com.typesafe.scalalogging.StrictLogging
import com.typesafe.sslconfig.akka.AkkaSSLConfig
import skierApp.actors.ActorImplicits._
import skierApp.rest.{Controller, ExampleController, StatisticsController}
import skierApp.utils.Settings

import scala.util.{Failure, Success}

object Main extends App with StrictLogging {

  val sslConfig = AkkaSSLConfig()
  val password = Settings.password.toCharArray
  val ks: KeyStore = KeyStore.getInstance("PKCS12")
  val keystore: InputStream = getClass.getClassLoader.getResourceAsStream("narciarz.p12")

  require(keystore != null, "Keystore required!")
  ks.load(keystore, password)

  val keyManagerFactory: KeyManagerFactory = KeyManagerFactory.getInstance("SunX509")
  keyManagerFactory.init(ks, password)


  val tmf: TrustManagerFactory = TrustManagerFactory.getInstance("SunX509")
  tmf.init(ks)

  val sslContext: SSLContext = SSLContext.getInstance("TLS")
  sslContext.init(keyManagerFactory.getKeyManagers, tmf.getTrustManagers, SecureRandom.getInstanceStrong)

  val https: HttpsConnectionContext = ConnectionContext.https(sslContext)


  val controllers: List[Controller] = List (
    new ExampleController,
    new StatisticsController
  )

  val routes = controllers.map(_.endpoints).reduce(_ ~ _)



  Http().bindAndHandle(handler = routes, interface = Settings.host, port = Settings.port, connectionContext = https)
    .onComplete {
      case Success(b) =>
        logger.info("Successfully bound to {}", b.localAddress)
      case Failure(ex) =>
        logger.error("Couldn't bind, because of {}", ex.getMessage)
    }

}
