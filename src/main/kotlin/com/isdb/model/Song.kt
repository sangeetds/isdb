package com.isdb.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
data class Song(
  @Id @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int,
  @Column(name = "name") val name: String = "",
  @OneToOne(mappedBy = "url") val url: Url = Url(""),
  @OneToOne(mappedBy = "album") val album: Album = Album(""),
  @Column(name = "year") val releaseDate: String = "",
  @Column(name = "user_ratings") val userRatings: Double = 0.0,
  @Column(name = "c_ratings") val criticsRatings: Double = 0.0,
  @Column(name = "votes") val votes: Int = 0,
  @Column(name = "s_id") val spotifyId: String = ""
)
