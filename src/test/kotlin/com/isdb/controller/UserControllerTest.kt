package com.isdb.controller

import com.isdb.model.User
import com.isdb.repository.UserRepository
import com.isdb.service.impl.UserServiceImplTest.Companion.sampleUser1
import com.isdb.service.impl.UserServiceImplTest.Companion.sampleUser2
import com.isdb.service.impl.UserServiceImplTest.Companion.sampleUser3
import com.isdb.service.impl.UserServiceImplTest.Companion.user
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class UserControllerTest {

  @Autowired lateinit var webTestClient: WebTestClient
  @Autowired lateinit var userRepository: UserRepository

  @BeforeEach
  fun setUp() {
    userRepository.deleteAll()
  }

  @Test
  fun `test registerUser when user already exists`() {
    val user = user()
    saveUsers(user)

    webTestClient.post().uri("/users/register")
      .body(Mono.just(user), User::class.java)
      .exchange()
      .expectStatus().isBadRequest
  }

  @Test
  fun `test registerUser when user doesn't exists`() {
    val user = user()
    saveUsers(null)

    webTestClient.post().uri("/users/register")
      .body(Mono.just(user), User::class.java)
      .exchange()
      .expectStatus().isOk
  }

  @Test
  fun `test loginUser when user already Exists`() {
    val user = user()
    saveUsers(user)


    webTestClient.post().uri("/users/login")
      .body(Mono.just(user), User::class.java)
      .exchange()
      .expectStatus().isOk
      .expectBody()
      .jsonPath("$.loggedIn").isEqualTo("true")
  }

  @Test
  fun `test loginUser when user does not exists`() {
    saveUsers(null)

    webTestClient.post().uri("/users/login")
      .body(Mono.just(user()), User::class.java)
      .exchange()
      .expectStatus().isNotFound
  }

  @Test
  fun `test deleteAllUsers`() {
    webTestClient.delete().uri("/users/all")
      .exchange()
      .expectStatus().isOk
  }

  @Test
  fun `test logUserOut when user does not exists`() {
    saveUsers(null)

    webTestClient.post().uri("/users/logout")
      .body(Mono.just(user()), User::class.java)
      .exchange()
      .expectStatus().isNotFound
  }

  @Test
  fun `test logUserOut when user already Exists`() {
    val user = user()
    saveUsers(user)

    webTestClient.post().uri("/users/logout")
      .body(Mono.just(user), User::class.java)
      .exchange()
      .expectStatus().isOk
      .expectBody()
      .jsonPath("$.loggedIn").isEqualTo("false")
  }

  @Test
  fun `test getLikedSongs`() {
    val user = User(
      id = "id", username = "sangeet", password = "any", email = "sangeet@sangeet.me",
      ratedSongIds = mutableListOf("songId1", "songId2", "songId3")
    )
    userRepository.save(user)

    webTestClient.get().uri("/users/songs/2}")
      .exchange()
      .expectStatus().isOk
      .expectBodyList(String::class.java)
  }

  private fun saveUsers(user: User?) {
    user?.let { userRepository.save(user()) }
    userRepository.save(sampleUser1)
    userRepository.save(sampleUser2)
    userRepository.save(sampleUser3)
  }
}