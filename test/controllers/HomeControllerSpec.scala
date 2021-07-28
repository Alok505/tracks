package controllers

import Models.JsonResponse._
import Models.{Availability, Titles, Track}
import Service.TrackService
import org.joda.time.DateTime
import org.scalatestplus.play._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._


class HomeControllerSpec extends PlaySpec {

  val service = new TrackService
  val controller = new HomeController(service, stubControllerComponents())
  "HomeController fetch" should {

    "return 200 and the correct track as Json if it exists in DB" in {
      val trackId = "nznx3r"
      val result = controller.fetch(trackId).apply(FakeRequest(GET, s"/track/$trackId"))

      val expectedResult = Json.toJson(Track("track", "nznx3r", "urn:bbc:sounds:track:nznx3r",
        Titles("AC/DC", "Highway to Hell"),
        Availability(DateTime.parse("2019-02-13T11:03:05Z"), DateTime.parse("2019-03-15T11:00:00Z"), "Available for 29 days")))

      status(result) mustBe OK
      contentAsJson(result) mustBe expectedResult
    }
  }
  "HomeController fetch" should{

    "return 404 and correct error message when track doesn't exist" in {

      val trackId = "SomeTrackId"
      val result = controller.fetch(trackId).apply(FakeRequest(GET, s"/track/$trackId"))
      val expectedResult = Json.toJson(TrackNotFound)

      status(result) mustBe NOT_FOUND
      contentAsJson(result) mustBe expectedResult
    }
  }

  "HomeController addTrack" should {
    "add a new track if it already doesn't exist in the DB" in {
     val numOfTracks = Helper.TrackList.size
      numOfTracks mustBe 1

      val newTrack = Track("track", "nznx6pr", "urn:bbc:sounds:track:nznx6pr",
        Titles("Led Zeppelin", "Immigrant Song"),
        Availability(DateTime.parse("2019-02-13T11:03:05Z"), DateTime.parse("2019-03-15T11:00:00Z"), "Available for 29 days"))

      val expectedResult = Json.toJson(TrackCreated)

      val result = controller.addTrack.apply(FakeRequest(POST, "/track/addTrack").withBody(Json.toJson(newTrack)))

      status(result) mustBe CREATED
      contentAsJson(result) mustBe expectedResult
    }

    "HomeController addTrack" should {
      "not add a new Track if it already exists" in {
        val newTrack = Track("track", "nznx3r", "urn:bbc:sounds:track:nznx3r",
          Titles("AC/DC", "Highway to Hell"),
          Availability(DateTime.parse("2019-02-13T11:03:05Z"), DateTime.parse("2019-03-15T11:00:00Z"), "Available for 29 days"))

        val expectedResult = Json.toJson(TrackAlreadyExists)

        val result = controller.addTrack.apply(FakeRequest(POST, "/track/addTrack").withBody(Json.toJson(newTrack)))

        status(result) mustBe CONFLICT

        contentAsJson(result) mustBe expectedResult

      }

    }
  }
}
