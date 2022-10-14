package com.oheyadam.library.data.source.hackernews.fake

import com.oheyadam.core.common.network.Result
import com.oheyadam.core.model.hackernews.Comment
import com.oheyadam.core.model.hackernews.Story
import com.oheyadam.library.data.source.hackernews.HackerNewsRemoteSource

class FakeHackerNewsRemoteSource : HackerNewsRemoteSource {

  override suspend fun getStory(storyId: Long): Result<Story> {
    return Result.Success(STORIES.first { story -> story.id == storyId })
  }

  override suspend fun getComment(commentId: Long): Result<Comment> {
    return Result.Success(COMMENTS.first { comment -> comment.id == commentId })
  }

  override suspend fun getTopStories(): Result<List<Story>> {
    return Result.Success(STORIES)
  }

  override suspend fun getNewStories(): Result<List<Story>> {
    return Result.Success(STORIES)
  }
}
