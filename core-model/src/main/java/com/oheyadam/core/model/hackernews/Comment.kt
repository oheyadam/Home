package com.oheyadam.core.model.hackernews

data class Comment(
	val id: Long,
	val deleted: Boolean?,
	val type: String,
	val author: String?,
	val time: Int?,
	val dead: Boolean?,
	val kids: List<Long>?,
	val parent: Int?,
	val text: String?
)
