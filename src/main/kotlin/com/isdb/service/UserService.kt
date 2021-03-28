package com.isdb.service

import com.isdb.model.User
import org.springframework.http.ResponseEntity

interface UserService {

  fun registerUser(newUser: User): ResponseEntity<User>

  fun loginUser(newUser: User): ResponseEntity<User>

  fun logOutUser(user: User): ResponseEntity<User>

  fun deleteAllUser()
***REMOVED***