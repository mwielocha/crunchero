import play.api.libs.json._
import landscape.common.UUIDHelper._
import landscape.common.UUIDImplicits._
import play.api.libs.json.JsSuccess
import scala.Some

/**
 * author mikwie
 *
 */
package object model {

  type UUID = com.eaio.uuid.UUID

  object UUID {

    def generate = new UUID

    implicit val UUIDReads = new Reads[UUID] {
      def reads(json: JsValue): JsResult[model.UUID] = {
        json.validate[String] match {
          case e: JsError => e
          case JsSuccess(raw, _) => {
            fromString(raw) match {
              case Some(uuid) => JsSuccess(uuid)
              case None => JsError(s"Malformed uuid: $json")
            }
          }
        }
      }
    }

    implicit val UUIDWrites = new Writes[UUID] {
      def writes(uuid: model.UUID): JsValue = {
        JsString(uuid.asString)
      }
    }
  }
}
