package com.isdb

import com.isdb.config.SpotifyDetails
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(SpotifyDetails::class)
class IsdbBackendApplication

fun main(args: Array<String>) {
  runApplication<IsdbBackendApplication>(*args)
}
