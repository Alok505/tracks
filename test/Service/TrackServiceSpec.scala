package Service

import Models.{Availability, Titles, Track}
import org.joda.time.DateTime
import org.scalatestplus.play.PlaySpec

class TrackServiceSpec extends PlaySpec {

  val service = new TrackService

  "getTrack" should {
    "get track if it exist in DB" in {
      val trackId = "nznx3r"
      val result = service.getTrack(trackId)
      val expectedResult = Track("track", "nznx3r", "urn:bbc:sounds:track:nznx3r",
        Titles("AC/DC", "Highway to Hell"),
        Availability(DateTime.parse("2019-02-13T11:03:05Z"), DateTime.parse("2019-03-15T11:00:00Z"), "Available for 29 days"))

      result mustBe Some(expectedResult)
    }

    "return None if track doesn't exist in DB" in {
      val trackId = "SomeTrackId"
      val result = service.getTrack(trackId)

      result mustBe None
    }

  }

  "trackAlreadyExists" should {
    "return true if track already exists in DB" in {
      val track = Track("track", "nznx3r", "urn:bbc:sounds:track:nznx3r",
        Titles("AC/DC", "Highway to Hell"),
        Availability(DateTime.parse("2019-02-13T11:03:05Z"), DateTime.parse("2019-03-15T11:00:00Z"), "Available for 29 days"))

      val result = service.trackAlreadyExists(track)

      result mustBe true
    }

    "return false if track doesnt exist in DB" in {
      val track = Track("track", "nznx6pr", "urn:bbc:sounds:track:nznx6pr",
        Titles("Led Zeppelin", "Immigrant Song"),
        Availability(DateTime.parse("2019-02-13T11:03:05Z"), DateTime.parse("2019-03-15T11:00:00Z"), "Available for 29 days"))

      val result = service.trackAlreadyExists(track)

      result  mustBe false

    }
  }

  "addTrack" should {
    "return 0 if it successfully manages to add a track" in {
      val track = Track("track", "nznx6pr", "urn:bbc:sounds:track:nznx6pr",
        Titles("Led Zeppelin", "Immigrant Song"),
        Availability(DateTime.parse("2019-02-13T11:03:05Z"), DateTime.parse("2019-03-15T11:00:00Z"), "Available for 29 days"))

      val result = service.addTrack(track)

      result mustBe 0
    }

    "return 1 if track already exists in DB" in {
      val track = Track("track", "nznx3r", "urn:bbc:sounds:track:nznx3r",
        Titles("AC/DC", "Highway to Hell"),
        Availability(DateTime.parse("2019-02-13T11:03:05Z"), DateTime.parse("2019-03-15T11:00:00Z"), "Available for 29 days"))

      val result = service.addTrack(track)

      result mustBe 1
    }


  }


}
