package com.isdb.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
data class Url(
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: String,
  @OneToOne val song: Song = Song(1),
  val spotify: String = ""
)