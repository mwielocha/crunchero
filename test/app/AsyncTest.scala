package app

import org.specs2.mutable.Specification
import scala.async.Async._
import scala.async.Async
import scala.concurrent.{ExecutionContext, Future}
import java.util.concurrent.{Executors, Executor}

/**
 * author mikwie
 *
 */
class AsyncTest extends Specification {

  implicit val defaultExecutionContext: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(10))



  "Asynch" should {

    "wait for first future to finish" in {



//      async {
//        Async.await(doSomething("one", 20)) + Async.await(doSomething("two", 20)) + Async.await(doSomething("three", 20))
//
//
//      }


//      for {
//        one <- doSomething("one", 20)
//        two <- doSomething("two", 20)
//        three <- doSomething("three", 20)
//      } yield {
//        println("All done")
//        1
//      }

      Future.fold(Seq(
        doSomething("one", 20),
        doSomething("two", 20),
        doSomething("three", 20)))(0L) {

        case (acc, f) => acc + f
      }

      Thread.sleep(100000)

      1 === 1


    }

  }
  
  def doSomething(name: String, delay: Long): Future[Long] = {
    val fut = Future {
      val r = (1 to 100).map(x => {
        println(s"$name: $x")
        Thread.sleep(delay)
        x
      }).sum.toLong

      println(s"$name is done")
      r
    }
    Thread.sleep(100)
    fut
  }
}
