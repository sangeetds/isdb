package com.isdb.mapper

import com.isdb.dto.SongDTO
import com.isdb.model.SimpleTrack
import com.isdb.model.Song

class TrackSongMapper {

  companion object {

    fun List<SimpleTrack>.toSongDTO(id: String): List<SongDTO> = this.map { track ->
      SongDTO(
        name = track.name,
        albumName = track.album.name,
        releaseDate = track.album.releaseDate,
        image = track.album.images,
        spotifyId = track.id,
        url = track.externalUrls.spotify,
        isUserRated = id.isNotBlank()
      )
    }

    fun List<Song>.getSongDTO(id: String): List<SongDTO> = this.map { song ->
      SongDTO(
        id = song.id!!,
        name = song.name,
        albumName = song.album.name,
        releaseDate = song.releaseDate,
        image = song.album.images,
        spotifyId = song.spotifyId,
        url = song.url.spotify,
        userRatings = song.userRatings,
        criticsRatings = song.criticsRatings,
        votes = song.votes,
        isUserRated = id.isNotBlank()
      )
    }
  }
}