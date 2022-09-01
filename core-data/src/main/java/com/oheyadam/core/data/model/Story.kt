package com.oheyadam.core.data.model

import com.oheyadam.core.model.Story
import com.oheyadam.core.network.model.StoryResponse

fun StoryResponse.toStory() = Story(
  author = author,
  descendants = descendants,
  id = id,
  kids = kids,
  score = score,
  time = time,
  title = title,
  type = type,
  url = url
)

fun List<StoryResponse>.toStories() = map { storyResponse -> storyResponse.toStory() }
