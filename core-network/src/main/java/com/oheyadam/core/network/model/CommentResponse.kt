package com.oheyadam.core.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

const val COMMENT_TYPE = "comment"

@JsonClass(generateAdapter = true)
data class CommentResponse(
  @Json(name = "id") val id: Long,
  @Json(name = "deleted") val deleted: Boolean?,
  @Json(name = "type") val type: String,
  @Json(name = "by") val author: String?,
  @Json(name = "time") val time: ReadableTimeResponse?,
  @Json(name = "dead") val dead: Boolean?,
  @Json(name = "kids") val kids: List<Long>?,
  @Json(name = "parent") val parent: Int?,
  @Json(name = "text") val text: String?,
)

fun ItemResponse.toCommentResponse() = CommentResponse(
  id = id,
  deleted = deleted,
  type = type,
  author = author,
  time = time,
  dead = dead,
  kids = kids,
  parent = parent,
  text = text
)
