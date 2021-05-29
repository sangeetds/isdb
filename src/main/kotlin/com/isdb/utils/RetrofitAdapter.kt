package com.isdb.utils

import com.isdb.config.SpotifyDetails
import com.isdb.endpoints.SpotifyEndpoints
import com.isdb.enums.Action
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Service
class RetrofitAdapter(@Autowired val spotifyDetails: SpotifyDetails) {

  fun getRetrofit(
    action: Action,
    token: String? = null
  ): SpotifyEndpoints {
    val moshi = Moshi.Builder()
      .addLast(KotlinJsonAdapterFactory())
      .build()

    val intercept =
      if (action == Action.AUTH) BasicInterceptor(
        spotifyDetails.id, spotifyDetails.secret
      )
      else BasicInterceptor(token!!)

    val httpClient = OkHttpClient.Builder().addInterceptor(intercept).build()

    val retrofit: Retrofit = Retrofit.Builder()
      .baseUrl(if (action == Action.AUTH) spotifyDetails.auth else spotifyDetails.search)
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .client(httpClient)
      .build()

    return retrofit.create(SpotifyEndpoints::class.java)
  }
}