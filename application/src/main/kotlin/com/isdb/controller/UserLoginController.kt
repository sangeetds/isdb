package com.isdb.controller

import login.enums.Status
import login.model.User
import login.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class UserLoginController
    (@Autowired private val userService: UserService) {

    @PostMapping("/users/register")
    fun registerUser(@RequestBody newUser: @Valid User?): Status {
        val users = this.userService.findAllUsers()

        for (user in users) {
            if (user!! == newUser && user.email == newUser.email) {
                println("User Already exists!")
                return Status.USER_ALREADY_EXISTS
          ***REMOVED***
      ***REMOVED***
        println(this.userService.saveUser(newUser!!))

        return Status.SUCCESS
  ***REMOVED***

    @PostMapping("/users/login")
    fun loginUser(@RequestBody user: @Valid User?): Status {
        val users = this.userService.findAllUsers()

        for (other in users) {
            if (other!! == user) {
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