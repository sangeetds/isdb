package com.isdb.controller

import com.isdb.enums.Status
import com.isdb.model.User
import com.isdb.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class UserLoginController
(@Autowired private val userService: UserService) {

  @PostMapping("/users/register")
  fun registerUser(@Valid @RequestBody newUser: User): Status {
    val users = this.userService.findAllUsers()

    for (user in users) {
      if (user!! == newUser && user.email == newUser.email) {
        println("User Already exists!")
        return Status.USER_ALREADY_EXISTS
    ***REMOVED***
  ***REMOVED***

    this.userService.saveUser(newUser)

    return Status.SUCCESS
***REMOVED***

  @PostMapping("/users/login")
  fun loginUser(@Valid @RequestBody user: User): Status {
    val users = this.userService.findAllUsers()

    for (other in users) {
      if (other!!.email == user.email && other.password == user.password) {
        user.isLoggedIn = true
        this.userService.saveUser(user)
        return Status.SUCCESS
    ***REMOVED***
  ***REMOVED***

    return Status.FAILURE
***REMOVED***

  @PostMapping("/users/logout")
  fun logUserOut(@RequestBody user: @Valid User?): Status {
    val users = this.userService.findAllUsers()

    for (other in users) {
      if (other!! == user) {
        user.isLoggedIn = false
        this.userService.saveUser(user)
        return Status.SUCCESS
    ***REMOVED***
  ***REMOVED***
    return Status.FAILURE
***REMOVED***

  @DeleteMapping("/users/all")
  fun deleteUsers(): Status {
    this.userService.deleteAllUser()

    return Status.SUCCESS
***REMOVED***
***REMOVED***