package com.isdb.controller

import com.isdb.dto.SongDTO
import com.isdb.model.Song
import com.isdb.model.UserSongDetailsDTO
import com.isdb.service.SongService
import io.swagger.v3.oas.annotations.Hidden
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.lang.Nullable
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SongsController(@Autowired val songService: SongService) {

  @GetMapping("tracks")
  fun getSongs(
    @Nullable @RequestParam("search") songName: String?
  ): ResponseEntity<List<SongDTO>> =
    ResponseEntity.ok().body(this.songService.getTracks(songName))

  @GetMapping("users/songs/{id}")
  fun getAllLikedSongs(@PathVariable("id") id: String): ResponseEntity<List<String>> =
    ResponseEntity.ok().body(this.songService.getLikedSongs(id))

  @PostMapping("tracks")
  fun rateSong(@RequestBody userSongDetailsDTO: UserSongDetailsDTO): ResponseEntity<Song> =
    ResponseEntity.ok().body(this.songService.saveTrack(userSongDetailsDTO))

  @Hidden
  @DeleteMapping("tracks")
  fun deleteRecords() = this.songService.deleteAllRecords()
}