package execution

import util.LoggingComponent
import scala.concurrent.ExecutionContext
import play.api.libs.concurrent.Akka

/**
 * author mikwie
 *
 */
trait DefaultExecutionContextComponent extends LoggingComponent {

  implicit val defaultExecutionContext: ExecutionContext
}
