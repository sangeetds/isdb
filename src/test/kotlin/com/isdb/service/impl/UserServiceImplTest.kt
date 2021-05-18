package com.isdb.service.impl

import com.isdb.model.User
import com.isdb.repository.UserRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import java.util.Optional
import com.google.common.truth.Truth.assertThat
import io.mockk.justRun
import io.mockk.verify

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

}