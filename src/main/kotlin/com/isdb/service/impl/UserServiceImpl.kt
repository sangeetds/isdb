package com.isdb.service.impl

import com.isdb.model.User
import com.isdb.repository.UserRepository
import com.isdb.service.UserService
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(@Autowired private val userRepository: UserRepository) : UserService {

  private val logger = KotlinLogging.logger {}

  override fun getUser(id: String): User? {
    this.logger.info { "Finding user by id: $id" }
    val user = this.userRepository.findById(id)

    if (user.isPresent) {
      this.logger.info { "Found user $user" }
      return user.get()
    }

    this.logger.info { "No user found by id: $id" }
    return null
  }

  override fun updateUser(user: User) {
    this.logger.info { "Saving user $user" }
    this.userRepository.save(user)
  }

  override fun registerUser(newUser: User): User? {
    val users = this.userRepository.findAll()
    this.logger.info { "Registering user $newUser" }

    users.forEach { user ->
      if (user.username == newUser.username || user.email == newUser.email) {

        this.logger.info { "User already Exists" }
        return null
      }
    }

    this.logger.info { "Successfully registered" }
    return this.userRepository.save(newUser)
  }

  override fun loginUser(newUser: User): User? {
    val users = this.userRepository.findAll()
    this.logger.info { "Logging-in user $newUser" }

    users.forEach { registeredUsers ->
      if (registeredUsers!!.email == newUser.email && registeredUsers.password == newUser.password) {
        registeredUsers.isLoggedIn = true

        this.logger.info { "User logged in" }
        return this.userRepository.save(registeredUsers)
      }
    }

    this.logger.info { "User not registered" }
    return null
  }

  override fun logOutUser(user: User): User? {
    val users = this.userRepository.findAll()
    this.logger.info { "Logging out user $user" }

    users.forEach { loggedInUsers ->
      if (loggedInUsers.email == user.email) {
        loggedInUsers.isLoggedIn = false

        this.logger.info { "Successfully logged out" }
        return this.userRepository.save(loggedInUsers)
      }
    }

    this.logger.info { "User not found" }
    return null
  }

  override fun deleteAllUser() =
    this.userRepository.deleteAll()

  override fun getLikedSongs(id: String): List<String> {
    this.logger.info { "Find liked songs by userId $id" }
    val user = this.userRepository.findById(id)

    if (user.isPresent) {
      this.logger.info { "Found user $user" }
      return user.get().ratedSongIds
    }

    this.logger.info { "No user with id $id found." }
    return listOf()
  }
}