package com.isdb.endpoints

import com.isdb.model.Authentications
import com.isdb.model.Items
import com.isdb.model.Tracks
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SpotifyEndpoints {

  @FormUrlEncoded
  @POST("/api/token")
  fun getAuthToken(
    @Field("grant_type") key: String = "client_credentials"
  ): Call<Authentications>

  @GET("/v1/search")
  fun getTracks(
    @Query("q") trackSearchValue: String,
    @Query("type") searchType: String
  ): Call<Tracks>

  @GET(value = "v1/tracks")
  fun getTrack(
    @Query("id") searchType: String
  ): Call<Items>
***REMOVED***

fun getToken(service: SpotifyEndpoints): Response<Authentications> =
  service.getAuthToken().execute()

fun getTracks(
  service: SpotifyEndpoints,
  trackSearchValue: String,
  searchType: String
): Response<Tracks> = service.getTracks(
  trackSearchValue,
  searchType
).execute()

fun getTrack(
  service: SpotifyEndpoints,
  trackSearchId: String
): Response<Items> = service.getTrack(trackSearchId).execute()
