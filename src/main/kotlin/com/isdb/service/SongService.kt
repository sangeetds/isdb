package com.isdb.service

import com.isdb.dto.SongDTO
import com.isdb.model.Song

interface SongService {

  fun getTracks(songName: String?): List<SongDTO>

  fun saveTrack(songDto: SongDTO): Song

  fun deleteAllRecords()
***REMOVED***