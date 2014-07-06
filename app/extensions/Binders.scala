package extensions

import play.api.mvc._
import com.eaio.uuid.UUID
import landscape.common.UUIDHelper
import play.api.mvc.PathBindable.Parsing

/**
 * author mikwie
 *
 */


object Binders {

  import landscape.common.UUIDImplicits._

  implicit def queryStringBindableUUID: QueryStringBindable[UUID] = {
    play.api.mvc.QueryStringBindable.bindableUUID.transform(x => x, x => x)
  }

  implicit def pathBindableUUID: PathBindable[UUID] = {
    play.api.mvc.PathBindable.bindableUUID.transform(x => x, x => x)
  }
}
