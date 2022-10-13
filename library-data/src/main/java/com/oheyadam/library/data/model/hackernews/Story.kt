package com.oheyadam.library.data.model.hackernews

import com.oheyadam.core.model.hackernews.Story
import com.oheyadam.core.network.model.hackernews.ItemResponse
import com.oheyadam.core.network.model.hackernews.StoryResponse

fun StoryResponse.toStory() = Story(
  id = id,
  deleted = deleted,
  type = type,
  author = author,
  time = time,
  dead = dead,
  kids = kids,
  descendants = descendants,
  score = score,
  title = title,
  url = url
)

fun List<StoryResponse>.toStories() = map { storyResponse -> storyResponse.toStory() }

fun ItemResponse.toStory() = Story(
  id = id,
  deleted = deleted,
  type = type,
  author = author,
  time = time,
  dead = dead,
  kids = kids,
  descendants = descendants,
  score = score,
  title = title,
  url = url
)
