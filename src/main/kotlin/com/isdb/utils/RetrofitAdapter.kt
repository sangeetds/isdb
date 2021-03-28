package com.isdb.utils

import com.isdb.config.SpotifyDetails.Companion.spotifyAuthApiUrl
import com.isdb.config.SpotifyDetails.Companion.spotifyClientId
import com.isdb.config.SpotifyDetails.Companion.spotifyClientSecret
import com.isdb.config.SpotifyDetails.Companion.spotifySearchApiUrl
import com.isdb.endpoints.SpotifyEndpoints
import com.isdb.enums.Action
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitAdapter {

***REMOVED***

    fun getRetrofit(
      action: Action,
      token: String? = null
    ): SpotifyEndpoints {
      val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

      val intercept =
        if (action == Action.AUTH) BasicInterceptor(spotifyClientId, spotifyClientSecret)
        else BasicInterceptor(token!!)

      val httpClient = OkHttpClient.Builder().addInterceptor(intercept).build()

      val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(if (action == Action.AUTH) spotifyAuthApiUrl else spotifySearchApiUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(httpClient)
        .build()

      return retrofit.create(SpotifyEndpoints::class.java)
  ***REMOVED***
***REMOVED***
***REMOVED***