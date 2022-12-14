package com.oheyadam.library.data.source

import com.oheyadam.core.common.network.Result
import com.oheyadam.core.common.network.Result.Error
import com.oheyadam.core.common.network.Result.Exception
import com.oheyadam.core.common.network.Result.Success
import com.oheyadam.core.model.Comment
import com.oheyadam.core.model.Story
import com.oheyadam.core.network.model.STORY_TYPE
import com.oheyadam.core.network.service.HackerNewsService
import com.oheyadam.library.data.model.toComment
import com.oheyadam.library.data.model.toStory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HackerNewsRemoteSourceImpl @Inject constructor(
  private val service: HackerNewsService,
) : HackerNewsRemoteSource {

  override suspend fun getStory(storyId: Long): Result<Story> {
    return when (val response = service.getItem(storyId)) {
      is Success -> {
        val data = response.data
        when {
          data.type != STORY_TYPE -> Exception(IllegalArgumentException())
          else -> Success(data.toStory())
        }
      }

      is Error -> Error(response.code)
      is Exception -> Exception(response.throwable)
    }
  }

  override suspend fun getComment(commentId: Long): Result<Comment> {
    return when (val response = service.getItem(commentId)) {
      is Success -> {
        val data = response.data
        when {
          data.type != STORY_TYPE -> Exception(IllegalArgumentException())
          else -> Success(data.toComment())
        }
      }

      is Error -> Error(response.code)
      is Exception -> Exception(response.throwable)
    }
  }

  override suspend fun getTopStoriesIds(): Result<List<Long>> {
    return when (val response = service.getTopStories()) {
      is Success -> Success(response.data)
      is Error -> Error(response.code)
      is Exception -> Exception(response.throwable)
    }
  }

  override suspend fun getNewStoriesIds(): Result<List<Long>> {
    return when (val response = service.getNewStories()) {
      is Success -> Success(response.data)
      is Error -> Error(response.code)
      is Exception -> Exception(response.throwable)
    }
  }
}
