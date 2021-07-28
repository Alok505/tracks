package Models

import play.api.libs.json.{Json, OFormat}

case class Track(`type`:String, id:String, urn:String, titles:Titles, availability:Availability)

object Track {
  implicit val Oformat: OFormat[Track] = Json.format[Track]
}
