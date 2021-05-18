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
  fun registerUser(@RequestBody newUser: User): ResponseEntity<User> {
    val registerUser = this.userService.registerUser(newUser)

    registerUser?.let {
      return ResponseEntity.ok().body(registerUser)
    }

    return ResponseEntity.badRequest().build()
  }

  @PostMapping("/users/login")
  fun loginUser(@RequestBody user: User): ResponseEntity<User> {
    val loginUser = this.userService.loginUser(user)

    loginUser?.let {
      return ResponseEntity.ok().body(loginUser)
    }

    return ResponseEntity.notFound().build()
  }

  @PostMapping("/users/logout")
  fun logUserOut(@RequestBody user: User): ResponseEntity<User> {
    val logOutUser = this.userService.logOutUser(user)

    logOutUser?.let {
      return ResponseEntity.ok(logOutUser)
    }

    return ResponseEntity.badRequest().build()
  }

  @Hidden
  @DeleteMapping("/users/all")
  fun deleteUsers() =
    this.userService.deleteAllUser()
}