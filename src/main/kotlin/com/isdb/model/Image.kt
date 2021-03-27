package com.isdb.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Image(
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val url: String,
  val height: Int = 0,
  val width: Int = 0,
)
