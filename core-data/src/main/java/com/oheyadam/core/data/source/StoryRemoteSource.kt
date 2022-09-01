package com.oheyadam.core.data.source

import com.oheyadam.core.data.model.toStories
import com.oheyadam.core.model.Story
import com.oheyadam.core.network.service.HackerNewsService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoryRemoteSource @Inject constructor(
  private val service: HackerNewsService
) {

  suspend fun getTopStories(): List<Story> {
    return service.getTopStories().toStories()
  }
}
