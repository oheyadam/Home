package com.oheyadam.library.data.model.hackernews

import com.oheyadam.core.model.hackernews.Comment
import com.oheyadam.core.network.model.hackernews.CommentResponse
import com.oheyadam.core.network.model.hackernews.ItemResponse

fun CommentResponse.toComment() = Comment(
  id = id,
  deleted = deleted,
  type = type,
  author = author,
  time = time?.toReadableTime(),
  dead = dead,
  kids = kids,
  parent = parent,
  text = text
)

fun ItemResponse.toComment() = Comment(
  id = id,
  deleted = deleted,
  type = type,
  author = author,
  time = time?.toReadableTime(),
  dead = dead,
  kids = kids,
  parent = parent,
  text = text
)
