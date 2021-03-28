package com.isdb.model

import com.squareup.moshi.Json

data class Album(
  val id: String = "",
  val name: String = "",
  @Json(name = "release_date") val releaseDate: String = "",
  val images: List<Image> = listOf(),
)
