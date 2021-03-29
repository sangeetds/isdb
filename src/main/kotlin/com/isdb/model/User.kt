package com.isdb.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User(
  @Id var id: String?,
  val username: String = "",
  var password: String = "",
  val email: String = "",
  var isLoggedIn: Boolean = false,
  val ratedSongIds: MutableList<String> = mutableListOf()
)