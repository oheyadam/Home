package com.oheyadam.core.testing.model.domain

import com.oheyadam.core.model.Comment
import com.oheyadam.core.model.ReadableTime.JustNow
import com.oheyadam.core.network.model.COMMENT_TYPE

val COMMENT_1 = Comment(
  id = 1,
  deleted = false,
  type = COMMENT_TYPE,
  author = "author",
  time = JustNow,
  dead = false,
  kids = listOf(3, 4),
  parent = -1,
  text = "Comment"
)
val COMMENT_2 = COMMENT_1.copy(id = 2, kids = emptyList())
val COMMENT_3 = COMMENT_1.copy(id = 3, kids = emptyList())
val COMMENT_4 = COMMENT_1.copy(id = 4, kids = listOf(5))
val COMMENT_5 = COMMENT_1.copy(id = 5, kids = listOf(6, 7))
val COMMENT_6 = COMMENT_1.copy(id = 6, kids = emptyList())
val COMMENT_7 = COMMENT_1.copy(id = 7, kids = emptyList())

val COMMENTS = listOf(COMMENT_1, COMMENT_2, COMMENT_3, COMMENT_4, COMMENT_5, COMMENT_6, COMMENT_7)
