package com.isdb.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "song")
// TODO(Make the fields as objects)
// TODO(Return objects)
// TODO(Deserialze name properly)
// TODO(Image is coming as empty)
// TODO(Release is empty)
// TODO(URL should be there)
data class Song(
  @Id @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int = 0,
  @Column(name = "name") val name: String = "",
  @Column(name = "uri") val uri: String = "",
  @Column(name = "album") val album: String = "",
  @Column(name = "year") val releaseDate: String = "",
  @Column(name = "image") val image: String? = "",
  @Column(name = "user_ratings") val userRatings: Double = 0.0,
  @Column(name = "c_ratings") val criticsRatings: Double = 0.0,
  @Column(name = "votes") val votes: Int = 0,
  @Column(name = "s_id") val spotifyId: String = ""
)
