package com.isdb.controller

import com.isdb.dto.SongDTO
import com.isdb.dto.UserSongDetailsDTO
import com.isdb.mapper.TrackSongMapper.Companion.getSongDTO
import com.isdb.repository.SongRepository
import com.isdb.repository.UserRepository
import com.isdb.service.impl.SongServiceImplTest.Companion.song
import com.isdb.service.impl.UserServiceImplTest.Companion.user
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpHeaders.ACCEPT
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class SongsControllerTest {

  @Autowired lateinit var webTestClient: WebTestClient
  @Autowired lateinit var songRepository: SongRepository
  @Autowired lateinit var userRepository: UserRepository

  @BeforeAll
  fun setUp() {
    songRepository.deleteAll()
    userRepository.deleteAll()
  }

  @Test
  fun `test getSongs when no song name passed`() {
    songRepository.save(song)
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val jsonAdapter: JsonAdapter<List<SongDTO>> =
      moshi.adapter(Types.newParameterizedType(List::class.java, SongDTO::class.java))
    val json = jsonAdapter.toJson(listOf(song).getSongDTO())

    webTestClient.get().uri("/tracks/")
      .exchange()
      .expectStatus().is2xxSuccessful
      .expectBody()
      .json(json)
  }

  @Test
  fun `test rateSong when track present`() {
    songRepository.save(song)
    userRepository.save(user())
    val userSongDetailsDTO = UserSongDetailsDTO(
      songId = "1",
      userRatings = 4.0,
      votes = 10,
      spotifyId = "spotify",
      userId = "userID"
    )

    song.userRatings = userSongDetailsDTO.userRatings
    song.votes = userSongDetailsDTO.votes

    webTestClient.post().uri("/tracks")
      .body(Mono.just(userSongDetailsDTO), UserSongDetailsDTO::class.java)
      .header(ACCEPT, APPLICATION_JSON_VALUE)
      .exchange()
      .expectStatus().is2xxSuccessful
      .expectBody()
      .jsonPath("$.id").isEqualTo(song.id!!.toString())
      .jsonPath("$.userRatings").isEqualTo(song.userRatings.toString())
      .jsonPath("$.criticsRatings").isEqualTo(song.criticsRatings.toString())
      .jsonPath("$.votes").isEqualTo(song.votes.toString())
  }

  @Test
  fun `test deleteRecords`() {
    webTestClient.delete().uri("/tracks")
      .exchange()
      .expectStatus().isOk
  }
}