package com.oheyadam.library.data.model

import com.oheyadam.core.model.Story
import com.oheyadam.core.network.model.ItemResponse
import com.oheyadam.core.network.model.StoryResponse

fun StoryResponse.toStory() = Story(
  id = id,
  deleted = deleted,
  type = type,
  author = author,
  time = time?.toReadableTime(),
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
  time = time?.toReadableTime(),
  dead = dead,
  kids = kids,
  descendants = descendants,
  score = score,
  title = title,
  url = url
)
