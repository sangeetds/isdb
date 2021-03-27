package com.isdb.model

import com.squareup.moshi.Json
import org.springframework.data.annotation.Id

data class Album(
  @Id val id: String,
  val name: String = "",
  @Json(name = "release_date") val releaseDate: String = "",
  val images: List<Image> = listOf(),
)
