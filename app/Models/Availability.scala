package Models


import play.api.libs.json.JodaWrites._
import play.api.libs.json.JodaReads._
import play.api.libs.json.{Json, OFormat}
import org.joda.time.DateTime


case class Availability(from:DateTime, to:DateTime, label:String)

object Availability {
  implicit val Oformat: OFormat[Availability] = Json.format[Availability]
}
