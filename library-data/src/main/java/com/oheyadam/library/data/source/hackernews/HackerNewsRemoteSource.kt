package com.oheyadam.library.data.source.hackernews

import com.oheyadam.core.common.network.Result
import com.oheyadam.core.model.hackernews.Comment
import com.oheyadam.core.model.hackernews.Story

interface HackerNewsRemoteSource {
  suspend fun getStory(storyId: Long): Result<Story>
  suspend fun getComment(commentId: Long): Result<Comment>
  suspend fun getTopStories(): Result<List<Story>>
  suspend fun getNewStories(): Result<List<Story>>
}
