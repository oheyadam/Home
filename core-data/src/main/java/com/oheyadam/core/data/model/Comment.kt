package com.oheyadam.core.data.model

import com.oheyadam.core.model.Comment
import com.oheyadam.core.network.model.CommentResponse

fun CommentResponse.toComment() = Comment(
  author = author,
  id = id,
  kids = kids,
  parent = parent,
  text = text,
  time = time,
  type = type
)
