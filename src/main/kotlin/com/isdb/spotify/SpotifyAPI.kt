package com.isdb.spotify

import com.isdb.endpoints.getToken
import com.isdb.endpoints.getTrack
import com.isdb.endpoints.getTracks
import com.isdb.enums.Action
import com.isdb.model.Authentications
import com.isdb.model.SimpleTrack
import com.isdb.utils.RetrofitAdapter
import mu.KotlinLogging

class SpotifyAPI {

  private fun getAuthToken(): Authentications? {
    val retrofitService = RetrofitAdapter.getRetrofit(action = Action.AUTH)
    val auth = getToken(retrofitService)
    return auth.body()
***REMOVED***

  fun getUserTracks(track: String): List<SimpleTrack> {
    val token = getAuthToken()?.accessToken ?: ""
    val retrofitService = RetrofitAdapter.getRetrofit(action = Action.SEARCH, token = token)
    val tracks = getTracks(service = retrofitService, trackSearchValue = track, "track")

    return when {
      tracks.isSuccessful -> tracks.body()!!.items.tracksList
      tracks.errorBody() != null -> listOf()
      else -> listOf()
  ***REMOVED***
***REMOVED***

  fun getUserTrack(spotifyId: String): SimpleTrack {
    val token = getAuthToken()?.accessToken ?: ""
    val retrofitService = RetrofitAdapter.getRetrofit(action = Action.TRACK, token = token)
    val track = getTrack(service = retrofitService, trackSearchId = spotifyId)

    return when {
      track.isSuccessful -> track.body()!!.tracks.first()
      else -> SimpleTrack()
  ***REMOVED***
***REMOVED***
***REMOVED***