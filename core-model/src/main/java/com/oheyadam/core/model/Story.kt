package com.oheyadam.core.model

data class Story(
  val id: Long,
  val deleted: Boolean?,
  val type: String,
  val author: String?,
  val time: ReadableTime?,
  val dead: Boolean?,
  val kids: List<Long>?,
  val descendants: Int?,
  val score: Int?,
  val title: String?,
  val url: String?,
)
