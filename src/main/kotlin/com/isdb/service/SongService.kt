package com.isdb.service

import com.isdb.dto.SongDTO
import com.isdb.model.Song
import com.isdb.model.UserSongDetailsDTO
import org.springframework.http.ResponseEntity

interface SongService {

  fun getTracks(songName: String?): List<SongDTO>

  fun saveTrack(userSongDetailsDTO: UserSongDetailsDTO): Song

  fun deleteAllRecords()

  fun getLikedSongs(id: String): ResponseEntity<List<String>>
***REMOVED***