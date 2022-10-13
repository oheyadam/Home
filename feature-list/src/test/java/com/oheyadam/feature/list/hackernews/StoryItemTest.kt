package com.oheyadam.feature.list.hackernews

import com.google.common.truth.Truth.assertThat
import com.oheyadam.feature.list.hackernews.model.STORY_ITEM
import org.junit.Test

class StoryItemTest {

  @Test
  fun `correctly shorten https urls`() {
    val actual = STORY_ITEM.shortenedUrl()
    val expected = "google.com"

    assertThat(actual).isEqualTo(expected)
  }

  @Test
  fun `correctly shorten http urls`() {
    val actual = STORY_ITEM.copy(url = "http://www.google.com").shortenedUrl()
    val expected = "google.com"

    assertThat(actual).isEqualTo(expected)
  }

  @Test
  fun `assign as trending if comments and votes count is higher than 100`() {
    assertThat(STORY_ITEM.isTrending()).isTrue()
  }

  @Test
  fun `assign as not trending if comments and votes count is lower than 100`() {
    assertThat(STORY_ITEM.copy(descendants = 10, score = 50).isTrending()).isFalse()
  }
}
