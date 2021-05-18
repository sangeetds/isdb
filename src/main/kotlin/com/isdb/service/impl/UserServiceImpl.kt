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

  override fun registerUser(newUser: User): ResponseEntity<User> {
    val users = this.userRepository.findAll()
    this.logger.info { "Registering user $newUser" }

    for (user in users) {
      if (user.username == newUser.username || user.email == newUser.email) {

        this.logger.info { "User already Exists" }
        return ResponseEntity.badRequest().build()
      }
    }

    this.logger.info { "Successfully registered" }
    return ResponseEntity.ok(this.userRepository.save(newUser))
  }

  override fun loginUser(newUser: User): ResponseEntity<User> {
    val users = this.userRepository.findAll()
    this.logger.info { "Logging-in user $newUser" }

    for (other in users) {
      if (other!!.email == newUser.email && other.password == newUser.password) {
        other.isLoggedIn = true

        this.logger.info { "User logged in" }
        return ResponseEntity.ok(this.userRepository.save(other))
      }
    }

    this.logger.info { "User not registered" }
    return ResponseEntity.notFound().build()
  }

  override fun logOutUser(user: User): ResponseEntity<User> {
    val users = this.userRepository.findAll()
    this.logger.info { "Logging out user $user" }

    for (otherUsers in users) {
      if (otherUsers.email == user.email) {
        otherUsers.isLoggedIn = false

        this.logger.info { "Successfully logged out" }
        return ResponseEntity.ok(this.userRepository.save(otherUsers))
      }
    }

    this.logger.info { "User not found" }
    return ResponseEntity.notFound().build()
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