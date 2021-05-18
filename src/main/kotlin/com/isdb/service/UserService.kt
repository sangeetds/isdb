package com.isdb.service

import com.isdb.model.User
import org.springframework.http.ResponseEntity

interface UserService {

  fun getUser(id: String): User?

  fun updateUser(user: User)

  fun registerUser(newUser: User): User?

  fun loginUser(newUser: User): User?

  fun logOutUser(user: User): User?

  fun deleteAllUser()

  fun getLikedSongs(id: String): List<String>
}