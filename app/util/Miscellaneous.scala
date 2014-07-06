package util

import play.api.libs.json._
import play.api.libs.functional.syntax._
import org.joda.time.DateTime

/**
 * author mikwie
 *
 */
object Miscellaneous {

  implicit val dateTimeReads = new Reads[DateTime] {
    def reads(json: JsValue): JsResult[DateTime] = {
      JsSuccess(
        new DateTime((json \ "timestamp").as[Long])
      )
    }
  }



  implicit val dateTimeWrites = new Writes[DateTime] {
    def writes(o: DateTime): JsValue = {
      Json.obj(
        ("timestamp" -> o.getMillis)
      )
    }
  }
}
