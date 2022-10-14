package com.oheyadam.feature.list.model

import com.oheyadam.core.model.ReadableTime.JustNow

val STORY_ITEM = StoryItem(
  id = 1,
  author = "author",
  time = JustNow,
  kids = emptyList(),
  descendants = 100,
  score = 100,
  title = "title",
  url = "https://www.google.com"
)
