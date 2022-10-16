package com.oheyadam.library.data.source

import com.oheyadam.core.common.network.Result
import com.oheyadam.core.model.Comment
import com.oheyadam.core.model.Story

interface HackerNewsRemoteSource {
  suspend fun getStory(storyId: Long): Result<Story>
  suspend fun getComment(commentId: Long): Result<Comment>
  suspend fun getTopStoriesIds(): Result<List<Long>>
  suspend fun getNewStoriesIds(): Result<List<Long>>
}
