package com.isdb.repository

import com.isdb.model.Song
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface SongsRepository : MongoRepository<Song, String> {

  fun findSongsByName(searchName: String): List<Song>
***REMOVED***
