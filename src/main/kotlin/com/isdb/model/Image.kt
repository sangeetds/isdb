package com.isdb.model

import org.springframework.data.annotation.Id

data class Image(
  @Id val url: String,
  val height: Int = 0,
  val width: Int = 0,
)
