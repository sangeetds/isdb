package com.isdb.service.impl

import com.isdb.dto.SongDTO
import com.isdb.mapper.TrackSongMapper.Companion.getSongDTO
import com.isdb.mapper.TrackSongMapper.Companion.toSongDTO
import com.isdb.model.Song
import com.isdb.repository.SongsRepository
import com.isdb.service.SongService
import com.isdb.spotify.SpotifyAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SongServiceImpl(@Autowired val songsRepository: SongsRepository) : SongService {

  val spotifyApi = SpotifyAPI()

  override fun getTracks(songName: String?): List<SongDTO> {
    songName?.let {
      val ratedSongs = this.songsRepository.findSongsByName(songName).getSongDTO()
      val searchSongs = spotifyApi.getUserTracks(songName).toSongDTO()

      return ratedSongs + searchSongs
  ***REMOVED***

    return this.songsRepository.findAll().getSongDTO()
***REMOVED***

  override fun saveTrack(songDto: SongDTO): Song {
    val track = spotifyApi.getUserTrack(songDto.spotifyId)
    val songToBeSaved = Song(songDto.id, track.name)

    return this.songsRepository.save(songToBeSaved)
***REMOVED***
***REMOVED***