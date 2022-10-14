package com.oheyadam.core.testing.model.domain

import com.oheyadam.core.model.ReadableTime.JustNow
import com.oheyadam.core.model.Story
import com.oheyadam.core.network.model.STORY_TYPE

val STORY_1 = Story(
  id = 1,
  deleted = false,
  type = STORY_TYPE,
  author = "author",
  time = JustNow,
  dead = false,
  kids = listOf(1, 2),
  descendants = 7,
  score = 12,
  title = "Title",
  url = "https://news.ycombinator.com/"
)

val STORY_2 = Story(
  id = 2,
  deleted = false,
  type = STORY_TYPE,
  author = "author",
  time = JustNow,
  dead = false,
  kids = listOf(1, 2),
  descendants = 7,
  score = 12,
  title = "Title",
  url = "https://news.ycombinator.com/"
)

val STORIES = listOf(STORY_1, STORY_2)
