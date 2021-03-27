package com.isdb.mapper

import com.isdb.dto.SongDTO
import com.isdb.model.Album
import com.isdb.model.Image
import com.isdb.model.SimpleTrack
import com.isdb.model.Song
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.UUID
import java.util.logging.Logger
import kotlin.random.Random

class TrackSongMapper {

***REMOVED***
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    fun List<SimpleTrack>.toSongDTO(): List<SongDTO> = this.map { track ->
        SongDTO(
          name = track.name,
          albumName = track.album.name,
          releaseDate = track.album.releaseDate,
          image = track.album.images,
          spotifyId = track.id,
          url = track.externalUrls.spotify
        )
    ***REMOVED***.distinctBy { it.spotifyId ***REMOVED***

    fun List<Song>.getSongDTO(): List<SongDTO> = this.map { song ->
      SongDTO(
        id = song.id!!,
        name = song.name,
        albumName = song.album.name ?: "",
        releaseDate = song.releaseDate ?: "",
        image = song.album.images,
        spotifyId = song.spotifyId,
        url = song.url.spotify ?: ""
      )
  ***REMOVED***
***REMOVED***
***REMOVED***