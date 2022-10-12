package com.oheyadam.core.network.model.hackernews

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemResponse(
	@Json(name = "by") val author: String?,
	@Json(name = "dead") val dead: Boolean?,
	@Json(name = "deleted") val deleted: Boolean?,
	@Json(name = "descendants") val descendants: Int?,
	@Json(name = "id") val id: Long,
	@Json(name = "kids") val kids: List<Long>?,
	@Json(name = "parent") val parent: Int?,
	@Json(name = "parts") val parts: List<Int>?,
	@Json(name = "score") val score: Int?,
	@Json(name = "text") val text: String?,
	@Json(name = "time") val time: Int?,
	@Json(name = "title") val title: String?,
	@Json(name = "type") val type: String,
	@Json(name = "url") val url: String?
)
