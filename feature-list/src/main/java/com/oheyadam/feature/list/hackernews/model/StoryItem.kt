package com.oheyadam.feature.list.hackernews.model

import com.oheyadam.core.model.hackernews.ReadableTime
import com.oheyadam.core.model.hackernews.Story

data class StoryItem(
  val id: Long,
  val author: String,
  val time: ReadableTime,
  val kids: List<Long>,
  val descendants: Int,
  val score: Int,
  val title: String,
  val url: String,
) {

  fun shortenedUrl(): String {
    return url
      .replace("https://", "")
      .replace("http://", "")
      .replace("www.", "")
      .replaceAfter("/", "")
      .replace("/", "")
      .replaceAfter("?", "")
      .replace("?", "")
  }

  fun isTrending(): Boolean {
    return score + descendants > 100
  }
}

fun Story.toStoryItem() = StoryItem(
  id = id,
  author = author.orEmpty(),
  time = time ?: ReadableTime.JustNow,
  kids = kids.orEmpty(),
  descendants = descendants ?: 0,
  score = score ?: 0,
  title = title.orEmpty(),
  url = url.orEmpty()
)

fun List<Story>.toStoryItems() = map { story -> story.toStoryItem() }
