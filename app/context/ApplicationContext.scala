package context

import play.api.Configuration
import service.{AnalyticsServiceComponent}

/**
 * author mikwie
 *
 */

case class DefaultApplicationContext(configuration: Configuration) extends ApplicationContext

trait ApplicationContext extends AnalyticsServiceComponent {

  val analyticsService = new AnalyticsService

}
