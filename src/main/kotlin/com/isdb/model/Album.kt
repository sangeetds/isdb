package com.isdb.model

import com.squareup.moshi.Json
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Album(
  @Id val id: String,
  val name: String = "",
  @Json(name = "release_date") val releaseDate: String = "",
  val images: List<Image> = listOf(),
)
