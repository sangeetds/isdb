package com.isdb.spotify

import com.isdb.endpoints.getToken
import com.isdb.endpoints.getTrack
import com.isdb.endpoints.getTracks
import com.isdb.enums.Action
import com.isdb.model.SimpleTrack
import com.isdb.utils.RetrofitAdapter
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SpotifyAPI(@Autowired val retrofitAdapter: RetrofitAdapter) {

  private val logger = KotlinLogging.logger {}

  private fun getAuthToken(): String {
    logger.info { "Auth Token request from spotify" }
    val retrofitService = retrofitAdapter.getRetrofit(action = Action.AUTH)
    val auth = getToken(retrofitService)

    return when {
      auth.isSuccessful -> {
        logger.info { "Successfully fetched API tokens"}
        auth.body()!!.accessToken
      }
      else -> {
        logger.error { "Error while fetching API token with error message: ${auth.errorBody()}" }
        ""
      }
    }
  }

  fun getTracksWithQuery(track: String): List<SimpleTrack> {
    val token = getAuthToken()
    val retrofitService = retrofitAdapter.getRetrofit(action = Action.SEARCH, token = token)

    logger.info { "Tracks requested from Spotify." }
    val tracks = getTracks(service = retrofitService, trackSearchValue = track, "track")

    return when {
      tracks.isSuccessful -> {
        logger.info { "Tracks successfully retrieved from Spotify"}
        tracks.body()!!.items.tracksList
      }
      else -> {
        logger.error { "Error while retrieving tracks from Spotify with error message: ${tracks.errorBody()}" }
        listOf()
      }
    }
  }

  fun getUserTrack(spotifyId: String): SimpleTrack {
    val token = getAuthToken()
    val retrofitService = retrofitAdapter.getRetrofit(action = Action.TRACK, token = token)

    logger.info { "Getting track info from Spotify" }
    val track = getTrack(service = retrofitService, trackSearchId = spotifyId)

    return when {
      track.isSuccessful -> {
        logger.info { "Successfully retrieved track info from Spotify" }
        track.body()!!.tracks.first()
      }
      else -> {
        logger.error { "Error while retrieving tracks with error message: ${track.errorBody()}"}
        SimpleTrack()
      }
    }
  }
}