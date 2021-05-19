package com.isdb.controller

import com.isdb.model.User
import com.isdb.service.UserService
import io.swagger.v3.oas.annotations.Hidden
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class UserController(@Autowired private val userService: UserService) {

  @PostMapping("/register")
  fun registerUser(@RequestBody newUser: User): ResponseEntity<User> {
    val registerUser = this.userService.registerUser(newUser)

    registerUser?.let {
      return ResponseEntity.ok().body(registerUser)
    }

    return ResponseEntity.badRequest().build()
  }

  @PostMapping("/login")
  fun loginUser(@RequestBody user: User): ResponseEntity<User> {
    val loginUser = this.userService.loginUser(user)

    loginUser?.let {
      return ResponseEntity.ok().body(loginUser)
    }

    return ResponseEntity.notFound().build()
  }

  @PostMapping("/logout")
  fun logUserOut(@RequestBody user: User): ResponseEntity<User> {
    val logOutUser = this.userService.logOutUser(user)

    logOutUser?.let {
      return ResponseEntity.ok(logOutUser)
    }

    return ResponseEntity.notFound().build()
  }

  @Hidden
  @DeleteMapping("/all")
  fun deleteUsers() =
    this.userService.deleteAllUser()

  @GetMapping("/songs/{id}")
  fun getAllLikedSongs(@PathVariable("id") id: String): ResponseEntity<List<String>> =
    ResponseEntity.ok().body(this.userService.getLikedSongs(id))
}