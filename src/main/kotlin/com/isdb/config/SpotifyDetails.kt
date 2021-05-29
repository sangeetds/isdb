package com.isdb.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.context.annotation.Configuration

@ConstructorBinding
@ConfigurationProperties("spotify")
data class SpotifyDetails(
  val id: String,
  val secret: String,
  val auth: String,
  val search: String,
)