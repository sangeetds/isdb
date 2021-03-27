package com.isdb.controller

import com.isdb.dto.SongDTO
import com.isdb.mapper.TrackSongMapper.Companion.getSongDTO
import com.isdb.mapper.TrackSongMapper.Companion.toSongDTO
import com.isdb.model.Song
import com.isdb.repository.SongsRepository
import com.isdb.spotify.SpotifyAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.lang.Nullable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SongsController(@Autowired val songsRepository: SongsRepository) {

  @GetMapping("tracks")
  fun getSongs(@Nullable @RequestParam("search") songName: String?): ResponseEntity<List<SongDTO>> {
    songName?.let {
      val ratedSongs = this.songsRepository.findSongsByName(songName).getSongDTO()
      val searchSongs = SpotifyAPI().getUserTrack(songName)
      println("searchSongs = ${searchSongs***REMOVED***")
      return ResponseEntity.ok().body(ratedSongs + searchSongs.toSongDTO())
  ***REMOVED***

    return ResponseEntity.ok().body(this.songsRepository.findAll().getSongDTO())
***REMOVED***

  @PostMapping("tracks")
  fun rateSong(@RequestBody song: Song): Song {
    println(song)
    println(song.url)
    return this.songsRepository.save(song)
***REMOVED***
***REMOVED***