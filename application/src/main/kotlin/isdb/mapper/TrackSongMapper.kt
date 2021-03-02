package isdb.mapper

import isdb.model.Song
import com.isdb.model.SimpleTrack

class TrackSongMapper {
  ***REMOVED***
        fun List<SimpleTrack>.toSongs(): List<Song> {
            return this.map { track ->
                Song(
                    name = track.name,
                    album = track.album.toString(),
                    releaseDate = track.album.releaseDate,
                    image = track.album.Images.toString(),
                    spotifyId = track.id
                )
          ***REMOVED***.distinctBy { it.spotifyId ***REMOVED***
      ***REMOVED***
  ***REMOVED***
***REMOVED***