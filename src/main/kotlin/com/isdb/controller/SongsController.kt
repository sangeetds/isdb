package com.isdb.controller

import com.isdb.dto.SongDTO
import com.isdb.model.Song
import com.isdb.service.SongService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.lang.Nullable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SongsController(@Autowired val songService: SongService) {

  @GetMapping("tracks")
  fun getSongs(@Nullable @RequestParam("search") songName: String?)
    : ResponseEntity<List<SongDTO>> = ResponseEntity.ok().body(this.songService.getTracks(songName))

  @PostMapping("tracks")
  fun rateSong(@RequestBody songDto: SongDTO): Song =
    this.songService.saveTrack(songDto)
***REMOVED***