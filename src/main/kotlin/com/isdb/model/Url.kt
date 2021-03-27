package com.isdb.model

import org.springframework.data.annotation.Id

data class Url(
  @Id val id: String,
  val song: Song = Song(1),
  val spotify: String = ""
)