package com.isdb.repository;

import com.isdb.model.Song;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface SongRepository extends MongoRepository<Song, String> {

  @Query("{'name':{'$regex':'?0','$options':'i'***REMOVED******REMOVED***")
  List<Song> searchByTitle(String name);
***REMOVED***