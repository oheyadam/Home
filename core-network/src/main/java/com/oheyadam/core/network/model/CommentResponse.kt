package com.oheyadam.core.network.model


import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class CommentResponse(
  @Json(name = "by")
  val author: String,
  @Json(name = "id")
  val id: Int,
  @Json(name = "kids")
  val kids: List<Int>,
  @Json(name = "parent")
  val parent: Int,
  @Json(name = "text")
  val text: String,
  @Json(name = "time")
  val time: Int,
  @Json(name = "type")
  val type: String
)
