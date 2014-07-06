package util

import scala.concurrent.{ExecutionContext, Future}

/**
 * author mikwie
 *
 */
object Performance {

  def mesurePerformance[T](function: => T)(postAction: Long => Unit): T = {
    val start = System.currentTimeMillis();
    val result = function
    postAction(System.currentTimeMillis() - start)
    result
  }

  def mesurePerformance[T](function: => Future[T])(postAction: Long => Unit)(implicit executor: ExecutionContext): Future[T] = {
    val start = System.currentTimeMillis();
    val result = function
    result.onComplete { x =>
      postAction(System.currentTimeMillis() - start)
    }
    result
  }
}
