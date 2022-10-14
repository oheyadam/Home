package com.oheyadam.library.data.model

import com.oheyadam.core.model.Comment
import com.oheyadam.core.network.model.CommentResponse
import com.oheyadam.core.network.model.ItemResponse

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
