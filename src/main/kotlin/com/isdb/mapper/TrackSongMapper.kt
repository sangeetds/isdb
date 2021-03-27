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
          id = (0..10000000).random(),
          name = track.name,
          albumName = track.album.name,
          releaseDate = track.album.releaseDate,
          image = track.album.images,
          spotifyId = track.id,
          url = track.externalUrls.spotify
        )
    ***REMOVED***.distinctBy { it.spotifyId ***REMOVED***

    fun List<Song>.getSongDTO(): List<SongDTO> = this.map { song ->
      Logger.getGlobal().info("$song")
      val album = readString(Album::class.java, song.album.toString())

      SongDTO(
        id = song.id,
        name = song.name,
        albumName = album?.name ?: "",
        releaseDate = album?.releaseDate ?: "",
        image = listFromString(Image::class.java, album?.images.toString()) ?: listOf(),
        spotifyId = song.spotifyId,
        url = song.url.spotify ?: ""
      )
  ***REMOVED***

    private fun <T> readString(type: Class<T>, jsonString: String) =
      this.moshi.adapter(type).fromJson(jsonString)

    private fun <T> listFromString(type: Class<T>, jsonString: String): List<T>? {
      val listMyData = Types.newParameterizedType(
        List::class.java, type
      )
      val adapter: JsonAdapter<List<T>> = moshi.adapter(listMyData)

      return adapter.fromJson(jsonString)
  ***REMOVED***
***REMOVED***
***REMOVED***