package execution

import scala.concurrent.ExecutionContext
import play.api.libs.concurrent.Akka
import play.api.Play.current

/**
 * author mikwie
 *
 */
trait ExecutionContextRegistryComponent {

  object Dispatchers {

    implicit val elasticsearchDispatcher: ExecutionContext = Akka.system.dispatchers.lookup("akka.actor.elasticsearch-dispatcher")

    implicit val filterUpdatingDispatcher: ExecutionContext = Akka.system.dispatchers.lookup("akka.actor.filter-updating-dispatcher")

    implicit val amqpConsumerDispatcher: ExecutionContext = Akka.system.dispatchers.lookup("akka.actor.amqp-consumer-dispatcher")
    
  }
}
