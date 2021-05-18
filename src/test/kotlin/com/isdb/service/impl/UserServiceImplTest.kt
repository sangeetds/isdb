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
    val newUser =
      User(id = "id", username = "sangeet", password = "any", email = "sangeet@sangeet.me")
    val sampleUser1 = User(id = "id2", username = "sampleName1", email = "sampleMail1@mail.co")
    val sampleUser2 = User(id = "id2", username = "sampleName2", email = "sampleMail2@mail.co")
    val sampleUser3 = User(id = "id3", username = "sampleName3", email = "sampleMail3@mail.co")

    every { userRepository.findAll() } returns listOf(
      newUser, sampleUser1, sampleUser2, sampleUser3
    )

    val registerUser = userService.registerUser(newUser)
    assertThat(registerUser).isNull()
  }

  @Test
  fun `test registerUser when user does not exists`() {
    val newUser =
      User(id = "id", username = "sangeet", password = "any", email = "sangeet@sangeet.me")

    every { userRepository.findAll() } returns listOf()
    every { userRepository.save(newUser) } returns newUser

    val registerUser = userService.registerUser(newUser)
    assertThat(registerUser).isNotNull()
    assertThat(registerUser).isEqualTo(newUser)
  }
}