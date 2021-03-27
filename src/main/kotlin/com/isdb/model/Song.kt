package com.isdb.model

import org.springframework.data.annotation.Id

data class Song(
   @Id val id: Int,
   val name: String = "",
   val url: Url = Url(""),
   val album: Album = Album(""),
   val releaseDate: String = "",
   val userRatings: Double = 0.0,
   val criticsRatings: Double = 0.0,
   val votes: Int = 0,
   val spotifyId: String = ""
)
