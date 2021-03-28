package com.isdb.service.impl

import com.isdb.dto.SongDTO
import com.isdb.mapper.TrackSongMapper.Companion.getSongDTO
import com.isdb.mapper.TrackSongMapper.Companion.toSongDTO
import com.isdb.model.Song
import com.isdb.repository.SongsRepository
import com.isdb.service.SongService
import com.isdb.spotify.SpotifyAPI
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SongServiceImpl(@Autowired val songsRepository: SongsRepository) : SongService {

  private val spotifyApi = SpotifyAPI()
  private val logger = KotlinLogging.logger {***REMOVED***

  override fun getTracks(songName: String?): List<SongDTO> {
    songName?.let {
      this.logger.info { "Fetching tracks for query $songName" ***REMOVED***
      val ratedSongs = this.songsRepository.findSongsByName(songName).getSongDTO()
      val searchSongs = spotifyApi.getUserTracks(songName).toSongDTO()
      val trackList = (ratedSongs + searchSongs).distinctBy { it.spotifyId ***REMOVED***

      this.logger.info { "Returning tracks with names ${trackList.map { it.name ***REMOVED******REMOVED***" ***REMOVED***
      return trackList
  ***REMOVED***

    return this.songsRepository.findAll().getSongDTO()
***REMOVED***

  override fun saveTrack(songDto: SongDTO): Song {
    this.logger.info { "Saving $songDto with id: ${songDto.id***REMOVED***" ***REMOVED***
    val track = spotifyApi.getUserTrack(songDto.spotifyId)
    this.logger.info { "Found the following track $track with id: ${track.id***REMOVED***" ***REMOVED***

    val songToBeSaved = Song(songDto.id, track.name)
    return this.songsRepository.save(songToBeSaved)
***REMOVED***
***REMOVED***