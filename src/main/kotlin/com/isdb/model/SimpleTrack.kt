package com.isdb.model

import com.squareup.moshi.Json
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne

data class SimpleTrack(
  @Json(name = "id") val id: String = "",
  @Json(name = "type") val type: String = "",
  @Json(name = "name") val name: String = "",
  @Json(name = "album") val album: Album = Album(""),
  @Json(name = "external_urls") val externalUrls: Url = Url("")
)
