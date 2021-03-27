package com.isdb.model

import com.squareup.moshi.Json
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.OneToOne

@Entity
data class Album(
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: String,
  val name: String = "",
  @Json(name = "release_date") @Column(name = "release_date") val releaseDate: String = "",
  @OneToMany val images: List<Image> = listOf(),
)
