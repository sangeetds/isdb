package com.isdb.controller

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SongsControllerTest {

  @Autowired lateinit var webTestClient: WebTestClient

  @Test
  fun getSongs() {
    webTestClient.get().uri("/")
  }

  @Test
  fun getAllLikedSongs() {
  }

  @Test
  fun rateSong() {
  }

  @Test
  fun deleteRecords() {
  }
}