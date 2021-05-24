package com.isdb.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.context.annotation.Configuration

@ConstructorBinding
@ConfigurationProperties("spotify")
data class SpotifyDetails(
  val clientId: String,
  val clientSecret: String,
  val apiAuth: String,
  val apiSearch: String,
)