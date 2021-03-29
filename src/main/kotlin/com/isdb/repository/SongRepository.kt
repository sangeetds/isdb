package com.isdb.repository;

import com.isdb.model.Song;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

interface SongRepository : MongoRepository<Song, String> {

  @Query("{'name':{'\$regex':'?0','\$options':'i'}}")
  fun searchByTitle(name: String): List<Song>
}