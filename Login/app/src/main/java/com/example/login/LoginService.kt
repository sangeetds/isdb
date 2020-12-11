package com.example.login

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("/users/register")
    fun createUser(
        @Body user: User
    ): Call<Status>

    @POST("repos/{owner***REMOVED***/{repo***REMOVED***/stats/contributors?per_page=100")
    fun logInUser(
        @Body user: User
    ): Call<Status>
***REMOVED***

fun createAccount(service: LoginService, user: User): Response<Status> = service
    .createUser(user)
    .execute()

fun logIn(service: LoginService, user: User): Response<Status> = service
    .logInUser(user)
    .execute()





