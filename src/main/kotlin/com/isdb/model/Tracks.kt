package com.isdb.model

import com.squareup.moshi.Json

data class Tracks(
  @Json(name = "tracks") val items: Item
)

data class Item(
  @Json(name = "items") val tracksList: List<SimpleTrack>
)
