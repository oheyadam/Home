package com.oheyadam.core.network.model


import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class StoryResponse(
  @Json(name = "by")
  val author: String,
  @Json(name = "descendants")
  val descendants: Int,
  @Json(name = "id")
  val id: Int,
  @Json(name = "kids")
  val kids: List<Int>,
  @Json(name = "score")
  val score: Int,
  @Json(name = "time")
  val time: Int,
  @Json(name = "title")
  val title: String,
  @Json(name = "type")
  val type: String,
  @Json(name = "url")
  val url: String
)
