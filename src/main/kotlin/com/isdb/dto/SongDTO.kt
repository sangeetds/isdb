package com.isdb.dto

import com.isdb.model.Image

data class SongDTO(
  val id: Int,
  val name: String = "",
  val albumName: String = "",
  val releaseDate: String = "",
  val image: List<Image> = listOf(),
  val userRatings: Double = 0.0,
  val criticsRatings: Double = 0.0,
  val votes: Int = 0,
  val spotifyId: String = "",
  val url: String = ""
)
