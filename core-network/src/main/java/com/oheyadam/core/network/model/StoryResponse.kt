package com.oheyadam.core.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

const val STORY_TYPE = "story"

@JsonClass(generateAdapter = true)
data class StoryResponse(
  @Json(name = "id") val id: Long,
  @Json(name = "deleted") val deleted: Boolean?,
  @Json(name = "type") val type: String,
  @Json(name = "by") val author: String?,
  @Json(name = "time") val time: ReadableTimeResponse?,
  @Json(name = "dead") val dead: Boolean?,
  @Json(name = "kids") val kids: List<Long>?,
  @Json(name = "descendants") val descendants: Int?,
  @Json(name = "score") val score: Int?,
  @Json(name = "title") val title: String?,
  @Json(name = "url") val url: String?,
)

fun ItemResponse.toStoryResponse() = StoryResponse(
  id = id,
  deleted = deleted,
  type = type,
  author = author,
  time = time,
  dead = dead,
  kids = kids,
  descendants = descendants,
  score = score,
  title = title,
  url = url
)
