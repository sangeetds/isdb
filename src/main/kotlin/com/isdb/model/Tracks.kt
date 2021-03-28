package com.isdb.model

import com.squareup.moshi.Json

data class Tracks(
  @Json(name = "tracks") val items: Items
)

data class SearchTrack(
  @Json(name = "tracks") val tracks: List<SimpleTrack>
)

data class Items(
  @Json(name = "items") val tracksList: List<SimpleTrack>
)
