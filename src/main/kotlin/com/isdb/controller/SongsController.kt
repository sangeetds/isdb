package com.isdb.controller

import com.isdb.dto.SongDTO
import com.isdb.dto.UserSongDetailsDTO
import com.isdb.model.Song
import com.isdb.service.SongService
import io.swagger.v3.oas.annotations.Hidden
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.lang.Nullable
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("tracks")
class SongsController(@Autowired val songService: SongService) {

  @GetMapping
  fun getSongs(
    @Nullable @RequestParam("search") songName: String?,
    @RequestParam userId: String
  ): ResponseEntity<List<SongDTO>> =
    ResponseEntity.ok().body(this.songService.getTracks(songName, userId))

  @PostMapping
  fun rateSong(@RequestBody userSongDetailsDTO: UserSongDetailsDTO): ResponseEntity<Song> {
    val saveTrack = this.songService.saveTrack(userSongDetailsDTO)

    saveTrack?.let {
      return ResponseEntity.ok().body(saveTrack)
    }

    return ResponseEntity.badRequest().build()
  }

  @Hidden
  @DeleteMapping
  fun deleteRecords() = this.songService.deleteAllRecords()
}