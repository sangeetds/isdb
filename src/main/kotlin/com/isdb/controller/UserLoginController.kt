package com.isdb.controller

import com.isdb.model.User
import com.isdb.service.UserService
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
    val users = this.userService.findAllUsers()

    for (user in users) {
      if (user!! == newUser && user.email == newUser.email) {

        return ResponseEntity.badRequest().build()
    ***REMOVED***
  ***REMOVED***

    return ResponseEntity.ok(this.userService.saveUser(newUser))
***REMOVED***

  @PostMapping("/users/login")
  fun loginUser(@RequestBody user: User): ResponseEntity<User> {
    val users = this.userService.findAllUsers()

    for (other in users) {
      if (other!!.email == user.email && other.password == user.password) {
        user.isLoggedIn = true

        return ResponseEntity.ok(this.userService.saveUser(user))
    ***REMOVED***
  ***REMOVED***

    return ResponseEntity.notFound().build()
***REMOVED***

  @PostMapping("/users/logout")
  fun logUserOut(@RequestBody user: User?): ResponseEntity<User> {
    val users = this.userService.findAllUsers()

    for (otherUsers in users) {
      if (otherUsers!! == user) {
        user.isLoggedIn = false

        return ResponseEntity.ok(this.userService.saveUser(user))
    ***REMOVED***
  ***REMOVED***

    return ResponseEntity.notFound().build()
***REMOVED***

  @DeleteMapping("/users/all")
  fun deleteUsers() =
    this.userService.deleteAllUser()
***REMOVED***