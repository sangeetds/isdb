package com.isdb.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Url(
  @Id val id: String,
  val spotify: String = ""
)