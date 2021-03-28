package com.isdb.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Song(
  @Id val id: String?,
  val name: String = "",
  val url: Url = Url(""),
  val album: Album = Album(""),
  val releaseDate: String = "",
  val userRatings: Double = 0.0,
  val criticsRatings: Double = 0.0,
  val votes: Int = 0,
  val spotifyId: String = ""
)
