package com.isdb.model

import com.squareup.moshi.Json

data class Tracks(
  @Json(name = "tracks") val items: Items
)

data class Items(
  @Json(name = "items") val tracksList: List<SimpleTrack>
)
