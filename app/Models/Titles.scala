package Models

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class Titles(primary: String, secondary: String, tertiary: Option[String] = None) {

}

object Titles {



  implicit val format: OFormat[Titles] = Json.format[Titles]
/*  implicit val titlesFormat: Format[Titles] = new Format[Titles] {
    override def writes(o: Titles): JsValue = Json.obj(
      "primary" -> o.primary,
      "secondary" -> o.secondary,
      "tertiary" -> o.tertiary
    )

    implicit val titlesReads: Reads[Titles] =
      ((JsPath \ "primary").read[String] and
        (JsPath \ "secondary").read[String] and
        (JsPath \ "tertiary").readNullable[String]
        ) (Titles.apply _)

    override def reads(json: JsValue): JsResult[Titles] = titlesReads.reads(json)
  }*/

}
