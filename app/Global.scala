package app

import play.api.{Play, Application, GlobalSettings}
import util.Logging
import context.{DefaultApplicationContext, ApplicationContext}
import play.api.Play.current

/**
 * author mikwie
 *
 */
object Global extends GlobalSettings with Logging {

  lazy val applicationContext: ApplicationContext = {
    DefaultApplicationContext(Play.configuration)
  }

  override def onStart(app: Application): Unit = {

    logger.info("Crunchero is starting up, please stand by...")

  }

  override def onStop(app: Application): Unit = {

  }
}
