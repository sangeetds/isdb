package com.isdb.controller

import com.isdb.model.User
import com.isdb.service.UserService
import io.swagger.v3.oas.annotations.Hidden
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserLoginController
(@Autowired private val userService: UserService) {

  @PostMapping("/users/register")
  fun registerUser(@RequestBody newUser: User): ResponseEntity<User> =
    this.userService.registerUser(newUser)

  @PostMapping("/users/login")
  fun loginUser(@RequestBody user: User): ResponseEntity<User> =
    this.userService.loginUser(user)

  @PostMapping("/users/logout")
  fun logUserOut(@RequestBody user: User): ResponseEntity<User> =
    this.userService.logOutUser(user)

  @Hidden
  @DeleteMapping("/users/all")
  fun deleteUsers() =
    this.userService.deleteAllUser()
***REMOVED***