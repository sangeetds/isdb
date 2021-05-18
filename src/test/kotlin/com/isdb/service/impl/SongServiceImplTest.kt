package com.isdb.service.impl

import com.google.common.truth.Truth.assertThat
import com.isdb.mapper.TrackSongMapper.Companion.getSongDTO
import com.isdb.model.Album
import com.isdb.model.Song
import com.isdb.model.Url
import com.isdb.model.User
import com.isdb.model.UserSongDetailsDTO
import com.isdb.repository.SongRepository
import com.isdb.service.UserService
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.util.Optional

class SongServiceImplTest {

  private val songRepository = mockk<SongRepository>()
  private val userService = mockk<UserService>()
  private val songService = SongServiceImpl(songRepository, userService)

  @Test
  fun `test getTracks when songName is not passed and no data stored`() {
    every { songRepository.findAll() } returns listOf()
    val tracks = songService.getTracks(null)
    assertThat(tracks).isEmpty()
  }

  @Test
  fun `test getTracks when songName is null`() {
    val songs = mutableListOf(song)

    every { songRepository.findAll() } returns songs

    val tracks = songService.getTracks(null)
    assertThat(tracks).isNotEmpty()
    assertThat(tracks).isEqualTo(songs.getSongDTO())
  }

  @Test
  fun `test saveTracks when tracks is present`() {
    val userSongDetailsDTO = UserSongDetailsDTO(
      songId = "songId",
      userRatings = 4.0,
      criticsRatings = 4.0,
      votes = 1,
      spotifyId = "spotifyId",
      userId = "userId"
    )

    val songToBeSaved = song
    val user = User(
      id = "userId", username = "username", email = "emailId", password = "password",
      isLoggedIn = true
    )
    every { songRepository.findById(userSongDetailsDTO.songId) } returns Optional.of(songToBeSaved)
    songToBeSaved.userRatings += userSongDetailsDTO.userRatings
    songToBeSaved.votes += userSongDetailsDTO.votes
    every { songRepository.save(any()) } returns songToBeSaved
    every { userService.getUser(userSongDetailsDTO.userId) } returns user
    justRun { userService.updateUser(user) }

    val savedTrack = songService.saveTrack(userSongDetailsDTO)
    assertThat(savedTrack).isNotNull()
    assertThat(savedTrack).isEqualTo(songToBeSaved)
  }

  @Test
  fun `test getLikedSongs returns liked songs of user`() {
    val id = "userId"
    val tracks = listOf("trackId1", "trackId2")

    every { userService.getLikedSongs(id) } returns tracks

    val likedSongs = songService.getLikedSongs(id)
    assertThat(likedSongs).isNotEmpty()
    assertThat(likedSongs).isEqualTo(tracks)
  }

  @Test
  fun `test deleteSongs`() {
    justRun { songRepository.deleteAll() }
    songService.deleteAllRecords()
    verify { songRepository.deleteAll() }
  }

  companion object {
    val song = Song(
      id = "1",
      name = "",
      url = Url(),
      album = Album(),
      releaseDate = "", userRatings = 0.0,
      criticsRatings = 0.0, votes = 0,
      spotifyId = ""
    )
  }
}