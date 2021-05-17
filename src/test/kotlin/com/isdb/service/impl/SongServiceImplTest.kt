package com.isdb.service.impl

import com.isdb.repository.SongRepository
import com.isdb.service.SongService
import com.isdb.service.UserService
import com.isdb.spotify.SpotifyAPI
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.kotlin.mock

class SongServiceImplTest {

  private val songRepository = mock<SongRepository>()
  private val userService = mock<UserService>()
  private val spotifyAPIMock = mock<SpotifyAPI>()
  private val songService = SongServiceImpl(songRepository, userService)

  @Test
  fun `test getTracks when songName is not passed `() {
    songService.getTracks(null)
  }

  @Test
  fun saveTrack() {
  }

  @Test
  fun deleteAllRecords() {
  }

  @Test
  fun getLikedSongs() {
  }

  @Test
  fun getSongRepository() {
  }

  @Test
  fun getUserService() {
  }
}