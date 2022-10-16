package com.oheyadam.library.data.source.fake

import com.oheyadam.core.common.network.Result
import com.oheyadam.core.model.Comment
import com.oheyadam.core.model.Story
import com.oheyadam.core.testing.model.domain.COMMENTS
import com.oheyadam.core.testing.model.domain.STORIES
import com.oheyadam.library.data.source.HackerNewsRemoteSource

class FakeHackerNewsRemoteSource : HackerNewsRemoteSource {

  override suspend fun getStory(storyId: Long): Result<Story> {
    return Result.Success(STORIES.first { story -> story.id == storyId })
  }

  override suspend fun getComment(commentId: Long): Result<Comment> {
    return Result.Success(COMMENTS.first { comment -> comment.id == commentId })
  }

  override suspend fun getTopStoriesIds(): Result<List<Story>> {
    return Result.Success(STORIES)
  }

  override suspend fun getNewStoriesIds(): Result<List<Story>> {
    return Result.Success(STORIES)
  }
}
