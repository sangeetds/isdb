package com.isdb.service.impl

import com.google.common.truth.Truth.assertThat
import com.isdb.model.User
import com.isdb.repository.UserRepository
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.util.Optional

class UserServiceImplTest {

  private val userRepository = mockk<UserRepository>()
  private val userService = UserServiceImpl(userRepository = userRepository)

  @Test
  fun `test getUser when user found`() {
    val id = "id"
    val expectedUser = User(id = "randomId", username = "name")

    every { userRepository.findById(id) } returns Optional.of(
      expectedUser
    )

    val user = userService.getUser(id)
    assertThat(user).isNotNull()
    assertThat(user).isEqualTo(expectedUser)
  }

  @Test
  fun `test getUser when no user found`() {
    val id = "id"

    every { userRepository.findById(id) } returns Optional.empty()

    val user = userService.getUser(id)
    assertThat(user).isNull()
  }

  @Test
  fun `test updateUser`() {
    val user = User(id = " id")
    every { userRepository.save(any()) } returns user

    userService.updateUser(user)
    verify { userRepository.save(any()) }
  }

  @Test
  fun `test registerUser when user already exists`() {
    val newUser = user()

    every { userRepository.findAll() } returns listOf(
      newUser, sampleUser1, sampleUser2, sampleUser3
    )

    val registerUser = userService.registerUser(newUser)
    assertThat(registerUser).isNull()
  }

  @Test
  fun `test registerUser when user does not exists`() {
    val newUser = user()

    every { userRepository.findAll() } returns listOf(
      User(id = "5", username = "nae", email = "mail")
    )
    every { userRepository.save(newUser) } returns newUser

    val registerUser = userService.registerUser(newUser)
    assertThat(registerUser).isNotNull()
    assertThat(registerUser).isEqualTo(newUser)
  }

  @Test
  fun `test loginUser when user does not exists`() {
    val newUser = user()

    every { userRepository.findAll() } returns listOf(
      User(id = "5", username = "nae", email = "mail")
    )

    val loginUser = userService.loginUser(newUser)
    assertThat(loginUser).isNull()
  }

  @Test
  fun `test loginUser when user does exists`() {
    val newUser = user()

    every { userRepository.findAll() } returns listOf(
      newUser, sampleUser1, sampleUser2, sampleUser3
    )
    every { userRepository.save(newUser) } returns newUser

    val loginUser = userService.loginUser(newUser)
    assertThat(loginUser).isNotNull()
    assertThat(loginUser).isEqualTo(newUser)
  }

  @Test
  fun `test logOutUser when user does not exists`() {
    val newUser = user()

    every { userRepository.findAll() } returns listOf(
      User(id = "5", username = "nae", email = "mail")
    )

    val loginUser = userService.logOutUser(newUser)
    assertThat(loginUser).isNull()
  }

  @Test
  fun `test logoutUser when user does exists`() {
    val newUser = user()

    every { userRepository.findAll() } returns listOf(
      newUser, sampleUser1, sampleUser2, sampleUser3
    )
    every { userRepository.save(newUser) } returns newUser

    val loginUser = userService.logOutUser(newUser)
    assertThat(loginUser).isNotNull()
    assertThat(loginUser).isEqualTo(newUser)
  }

  @Test
  fun `test deleteUser`() {
    justRun { userRepository.deleteAll() }

    userService.deleteAllUser()

    verify { userRepository.deleteAll() }
  }

  @Test
  fun `test getLikedSongs when user is present`() {
    val id = "id"
    val user =
      User(
        id = "id", username = "sangeet", password = "any", email = "sangeet@sangeet.me",
        ratedSongIds = mutableListOf("songId1", "songId2", "songId3")
      )

    every { userRepository.findById(id) } returns Optional.of(user)

    val likedSongs = userService.getLikedSongs(id)
    assertThat(likedSongs).isNotNull()
    assertThat(likedSongs)
      .containsExactly("songId1", "songId2", "songId3")
      .inOrder()
  }

  @Test
  fun `test getLikedSongs when no user found`() {
    val id = "id"

    every { userRepository.findById(id) } returns Optional.empty()

    val likedSongs = userService.getLikedSongs(id)
    assertThat(likedSongs).isNotNull()
    assertThat(likedSongs).isEmpty()
  }

  @Test
  fun `test getLikedSongs returns liked songs of user`() {
    val id = "userId"
    val tracks = listOf("trackId1", "trackId2")

    every { userService.getLikedSongs(id) } returns tracks

    val likedSongs = userService.getLikedSongs(id)
    assertThat(likedSongs).isNotEmpty()
    assertThat(likedSongs).isEqualTo(tracks)
  }

  companion object {
    val sampleUser1 = User(id = "id2", username = "sampleName1", email = "sampleMail1@mail.co")
    val sampleUser2 = User(id = "id2", username = "sampleName2", email = "sampleMail2@mail.co")
    val sampleUser3 = User(id = "id3", username = "sampleName3", email = "sampleMail3@mail.co")
    fun user() =
      User(id = "userID", username = "sangeet", password = "any", email = "sangeet@sangeet.me")
  }
}