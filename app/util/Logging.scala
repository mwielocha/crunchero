package util

import play.api.Logger

/**
 * author mikwie
 *
 */
trait Logging {

  val logger: play.api.Logger = Logger(getClass)

}
