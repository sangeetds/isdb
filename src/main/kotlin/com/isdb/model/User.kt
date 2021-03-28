package com.isdb.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User(
  @Id var id: String?,
  var username: String = "",
  var password: String = "",
  var email: String = "",
  var isLoggedIn: Boolean = false
)