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

  private val logger = KotlinLogging.logger {***REMOVED***

  override fun registerUser(newUser: User): ResponseEntity<User> {
    val users = this.userRepository.findAll()
    this.logger.info { "Registering user $newUser" ***REMOVED***

    for (user in users) {
      if (user!! == newUser && user.email == newUser.email) {

        this.logger.info { "User already Exists" ***REMOVED***
        return ResponseEntity.badRequest().build()
    ***REMOVED***
  ***REMOVED***

    this.logger.info { "Successfully registered" ***REMOVED***
    return ResponseEntity.ok(this.userRepository.save(newUser))
***REMOVED***

  override fun loginUser(newUser: User): ResponseEntity<User> {
    val users = this.userRepository.findAll()
    this.logger.info { "Logging-in user $newUser" ***REMOVED***

    for (other in users) {
      if (other!!.email == newUser.email && other.password == newUser.password) {
        newUser.isLoggedIn = true

        this.logger.info { "User logged in" ***REMOVED***
        return ResponseEntity.ok(this.userRepository.save(newUser))
    ***REMOVED***
  ***REMOVED***

    this.logger.info { "User not registered" ***REMOVED***
    return ResponseEntity.notFound().build()
***REMOVED***

  override fun logOutUser(user: User): ResponseEntity<User> {
    val users = this.userRepository.findAll()
    this.logger.info { "Logging out user $user" ***REMOVED***

    for (otherUsers in users) {
      if (otherUsers!! == user) {
        user.isLoggedIn = false

        this.logger.info { "Successfully registered" ***REMOVED***
        return ResponseEntity.ok(this.userRepository.save(user))
    ***REMOVED***
  ***REMOVED***

    this.logger.info { "User not found" ***REMOVED***
    return ResponseEntity.notFound().build()
***REMOVED***

  override fun deleteAllUser() {
    this.userRepository.deleteAll()
***REMOVED***
***REMOVED***