package com.oheyadam.core.model

data class Comment(
  val author: String,
  val id: Int,
  val kids: List<Int>,
  val parent: Int,
  val text: String,
  val time: Int,
  val type: String
)
