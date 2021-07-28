package Models

import play.api.http.Status._
import play.api.libs.json.{Json, OFormat}


case class JsonResponse(statusCode: Int, message: String)

object JsonResponse {

  implicit val format: OFormat[JsonResponse] = Json.format[JsonResponse]

  val  TrackNotFound =  JsonResponse(NOT_FOUND, "Track does not exists")
  val  TrackAlreadyExists =  JsonResponse(CONFLICT, "Track already exists")
  val  TrackCreated =  JsonResponse(CREATED, "Track created")

}



