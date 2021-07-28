package controllers

import Models._
import Service.TrackService
import javax.inject._
import play.api._
import play.api.libs.json.{JsError, Json}
import play.api.mvc._

import scala.concurrent.Future
import Models.JsonResponse._


/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController @Inject()(val service: TrackService, val controllerComponents: ControllerComponents) extends BaseController {

  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def fetch(trackId: String) = Action.async { implicit request =>
    service.getTrack(trackId) match {
      case Some(track) => Future.successful(Ok(Json.toJson(track)))
      case None => Future.successful(NotFound(Json.toJson(TrackNotFound)))
    }
  }

  def addTrack = Action.async(parse.json) { implicit request =>

    request.body.validate[Track].fold(
      error => Future.successful(BadRequest(JsError.toJson(error))),
      response => service.addTrack(response) match {
        case 1 => Future.successful(Conflict(Json.toJson(TrackAlreadyExists)))
        case 0 => Future.successful(Created(Json.toJson(TrackCreated)))
      }

    )
  }
}
