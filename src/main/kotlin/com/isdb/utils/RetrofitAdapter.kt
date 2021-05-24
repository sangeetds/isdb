package com.isdb.utils

import com.isdb.config.SpotifyDetails
import com.isdb.endpoints.SpotifyEndpoints
import com.isdb.enums.Action
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.springframework.beans.factory.annotation.Autowired
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitAdapter {

  companion object {

    @Autowired lateinit var spotifyDetails: SpotifyDetails

    fun getRetrofit(
      action: Action,
      token: String? = null
    ): SpotifyEndpoints {
      val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

      val intercept =
        if (action == Action.AUTH) BasicInterceptor(
          spotifyDetails.clientId, spotifyDetails.clientSecret
        )
        else BasicInterceptor(token!!)

      val httpClient = OkHttpClient.Builder().addInterceptor(intercept).build()

      val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(if (action == Action.AUTH) spotifyDetails.apiAuth else spotifyDetails.apiSearch)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(httpClient)
        .build()

      return retrofit.create(SpotifyEndpoints::class.java)
    }
  }
}