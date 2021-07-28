import Models.{Availability, Titles, Track}
import org.joda.time.DateTime

import scala.collection.mutable.ArrayBuffer

package object Helper {

  var TrackList: ArrayBuffer[Track] = ArrayBuffer(
    Track("track", "nznx3r", "urn:bbc:sounds:track:nznx3r",
      Titles("AC/DC", "Highway to Hell"),
      Availability(DateTime.parse("2019-02-13T11:03:05Z"), DateTime.parse("2019-03-15T11:00:00Z"), "Available for 29 days"))
  )
}
