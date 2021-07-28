package Service

import Models.Track

class TrackService {


  def getTrack(id: String): Option[Track] = Helper.TrackList.find(_.id == id)

  def addTrack(track:Track): Int = if(trackAlreadyExists(track)) 1 else {
    Helper.TrackList += track
    0
  }

  def trackAlreadyExists(track: Track) = Helper.TrackList.exists(existingTrack => existingTrack.id == track.id)


}
