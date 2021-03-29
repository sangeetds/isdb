package com.isdb.dto

import com.isdb.model.Image

data class SongDTO(
  val id: String = "",
  val name: String = "",
  val albumName: String = "",
  val releaseDate: String = "",
  val image: List<Image> = listOf(),
  val userRatings: Double = 0.0,
  val criticsRatings: Double = 0.0,
  val votes: Int = 0,
  val spotifyId: String = "",
  val url: String = ""
) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as SongDTO

    if (name != other.name) return false
    if (albumName != other.albumName) return false
    if (releaseDate != other.releaseDate) return false
    if (image != other.image) return false
    if (spotifyId != other.spotifyId) return false
    if (url != other.url) return false

    return true
  }

  override fun hashCode(): Int {
    var result = name.hashCode()
    result = 31 * result + albumName.hashCode()
    result = 31 * result + releaseDate.hashCode()
    result = 31 * result + image.hashCode()
    result = 31 * result + spotifyId.hashCode()
    result = 31 * result + url.hashCode()
    return result
  }
}
