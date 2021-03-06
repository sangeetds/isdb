package com.isdb.model

import com.squareup.moshi.Json
import javax.persistence.Id

data class Tracks(
    @Id @Json(name = "tracks") val items: Item
)

data class Item(
    @Json(name = "items") val tracksList: List<SimpleTrack>
)
