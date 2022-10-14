package com.oheyadam.core.model

data class Comment(
  val id: Long,
  val deleted: Boolean?,
  val type: String,
  val author: String?,
  val time: ReadableTime?,
  val dead: Boolean?,
  val kids: List<Long>?,
  val parent: Int?,
  val text: String?,
)
