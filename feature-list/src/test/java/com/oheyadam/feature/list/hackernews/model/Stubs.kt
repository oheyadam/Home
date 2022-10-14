package com.oheyadam.feature.list.hackernews.model

import com.oheyadam.core.model.hackernews.ReadableTime

val STORY_ITEM = StoryItem(
  id = 1,
  author = "author",
  time = ReadableTime.JustNow,
  kids = emptyList(),
  descendants = 100,
  score = 100,
  title = "title",
  url = "https://www.google.com"
)
