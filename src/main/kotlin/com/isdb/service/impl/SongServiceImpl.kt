package com.isdb.service.impl

import com.isdb.dto.SongDTO
import com.isdb.mapper.TrackSongMapper.Companion.getSongDTO
import com.isdb.mapper.TrackSongMapper.Companion.toSongDTO
import com.isdb.model.Song
import com.isdb.dto.UserSongDetailsDTO
import com.isdb.repository.SongRepository
import com.isdb.service.SongService
import com.isdb.service.UserService
import com.isdb.spotify.SpotifyAPI
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Objects

@Service
class SongServiceImpl(
  @Autowired val songRepository: SongRepository,
  @Autowired val userService: UserService
) : SongService {

  private val spotifyApi = SpotifyAPI();
  private val logger = KotlinLogging.logger {}

  override fun getTracks(songName: String?, userId: String): List<SongDTO> {
    songName?.let { name ->
      this.logger.info { "Fetching tracks for query $songName" }
      val trackList = this.spotifyApi.getTracksWithQuery(name).toSongDTO()

      this.logger.info { "Returning tracks with names ${trackList.map { it.name }}" }
      return trackList
    }

    val userRatedSongList = this.userService.getLikedSongs(userId).toHashSet()
    val songDTOs = this.songRepository.findAll().getSongDTO(userRatedSongList)
    this.logger.info { "Returning tracks with names ${songDTOs.map { it.name }}}" }

    return songDTOs
  }

  override fun saveTrack(userSongDetailsDTO: UserSongDetailsDTO): Song? {
    this.logger.info { "Saving $userSongDetailsDTO with id: ${userSongDetailsDTO.spotifyId}" }
    val track = this.songRepository.findById(userSongDetailsDTO.songId)
    val user = this.userService.getUser(userSongDetailsDTO.userId)

    return if (Objects.nonNull(user)) {
      val songToBeSaved: Song
      when (track.isPresent) {
        true -> {
          this.logger.info { "Found the following track in database $track with id: ${track.get().id}" }
          songToBeSaved = track.get()
          songToBeSaved.userRatings = userSongDetailsDTO.userRatings
          songToBeSaved.votes = userSongDetailsDTO.votes
        }
        else -> {
          this.logger.info { "Fetching the song from Spotify" }
          val trackFromSpotify = this.spotifyApi.getUserTrack(userSongDetailsDTO.spotifyId)
          this.logger.info { "Fetching successful, found track $trackFromSpotify" }

          songToBeSaved = Song(
            id = null,
            name = trackFromSpotify.name,
            url = trackFromSpotify.externalUrls,
            album = trackFromSpotify.album,
            releaseDate = trackFromSpotify.album.releaseDate,
            userRatings = userSongDetailsDTO.userRatings,
            criticsRatings = userSongDetailsDTO.criticsRatings,
            votes = userSongDetailsDTO.votes,
            spotifyId = trackFromSpotify.id,
          )
        }
      }

      this.logger.info { "Saving the following song in database $songToBeSaved" }
      val savedSong = songRepository.save(songToBeSaved)

      savedSong.id?.let { user?.ratedSongIds?.add(it) }
      this.userService.updateUser(user!!)

      savedSong
    }
    else
      null
  }

  override fun deleteAllRecords() = this.songRepository.deleteAll()
}