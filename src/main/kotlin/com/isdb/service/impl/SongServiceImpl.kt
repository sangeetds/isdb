package com.isdb.service.impl

import com.isdb.dto.SongDTO
import com.isdb.mapper.TrackSongMapper.Companion.getSongDTO
import com.isdb.mapper.TrackSongMapper.Companion.toSongDTO
import com.isdb.model.Song
import com.isdb.repository.SongRepository
import com.isdb.service.SongService
import com.isdb.spotify.SpotifyAPI
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SongServiceImpl(@Autowired val songRepository: SongRepository) : SongService {

  private val spotifyApi = SpotifyAPI()
  private val logger = KotlinLogging.logger {***REMOVED***

  override fun getTracks(songName: String?): List<SongDTO> {
    songName?.let {
      this.logger.info { "Fetching tracks for query $songName" ***REMOVED***
      val trackList = this.songRepository.searchByTitle(songName).getSongDTO().toMutableSet()
      this.logger.info { "Found following songs in database ${trackList.map { it.name ***REMOVED******REMOVED***"***REMOVED***

      spotifyApi.getUserTracks(songName).toSongDTO().forEach { track ->
        if (!trackList.contains(track)) {
          trackList.add(track)
      ***REMOVED***
    ***REMOVED***

      this.logger.info { "Returning tracks with names ${trackList.map { it.name ***REMOVED******REMOVED***" ***REMOVED***
      return trackList.toList()
  ***REMOVED***

    val songDTOs = this.songRepository.findAll().getSongDTO()
    this.logger.info { "Returning tracks with names ${songDTOs.map { it.name ***REMOVED******REMOVED******REMOVED***" ***REMOVED***

    return songDTOs
***REMOVED***

  override fun saveTrack(songDto: SongDTO): Song {
    this.logger.info { "Saving $songDto with id: ${songDto.id***REMOVED***" ***REMOVED***
    val track = this.songRepository.findById(songDto.id)
    val songToBeSaved: Song

    when (track.isPresent) {
      true -> {
        this.logger.info { "Found the following track in database $track with id: ${track.get().id***REMOVED***" ***REMOVED***
        songToBeSaved = track.get()
        songToBeSaved.userRatings = songDto.userRatings
        songToBeSaved.votes = songDto.votes
    ***REMOVED***
      else -> {
        this.logger.info { "Fetching the song from Spotify" ***REMOVED***
        val trackFromSpotify = this.spotifyApi.getUserTrack(songDto.spotifyId)
        this.logger.info { "Fetching successful, found track $trackFromSpotify" ***REMOVED***
        songToBeSaved = Song(
          id = null,
          name = trackFromSpotify.name,
          url = trackFromSpotify.externalUrls,
          album = trackFromSpotify.album,
          releaseDate = trackFromSpotify.album.releaseDate,
          userRatings = songDto.userRatings,
          criticsRatings = songDto.criticsRatings,
          votes = songDto.votes,
          spotifyId = trackFromSpotify.id,
        )
    ***REMOVED***
  ***REMOVED***

    this.logger.info { "Saving the following song in database $songToBeSaved" ***REMOVED***
    return songRepository.save(songToBeSaved)
***REMOVED***

  override fun deleteAllRecords() = this.songRepository.deleteAll()
***REMOVED***