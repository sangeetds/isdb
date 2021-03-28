package com.isdb.model

import org.springframework.data.mongodb.core.mapping.Document

data class Image(
  val url: String = "",
  val height: Int = 0,
  val width: Int = 0,
)
