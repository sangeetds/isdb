package com.isdb.service

import com.isdb.dto.SongDTO
import com.isdb.model.Song
import com.isdb.dto.UserSongDetailsDTO

interface SongService {

  fun getTracks(songName: String?): List<SongDTO>

  fun saveTrack(userSongDetailsDTO: UserSongDetailsDTO): Song

  fun deleteAllRecords()
}