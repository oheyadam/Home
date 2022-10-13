package com.oheyadam.library.data.source.hackernews.fake

import com.oheyadam.core.common.network.Result
import com.oheyadam.core.model.hackernews.Comment
import com.oheyadam.core.model.hackernews.Story
import com.oheyadam.core.network.model.hackernews.COMMENT_TYPE
import com.oheyadam.library.data.source.hackernews.HackerNewsRemoteSource

val COMMENT_1 = Comment(
  id = 1,
  deleted = false,
  type = COMMENT_TYPE,
  author = "author",
  time = 23,
  dead = false,
  kids = listOf(3, 4),
  parent = -1,
  text = "Comment"
)
val COMMENT_2 = COMMENT_1.copy(id = 2, kids = emptyList())
val COMMENT_3 = COMMENT_1.copy(id = 3, kids = emptyList())
val COMMENT_4 = COMMENT_1.copy(id = 4, kids = listOf(5))
val COMMENT_5 = COMMENT_1.copy(id = 5, kids = listOf(6, 7))
val COMMENT_6 = COMMENT_1.copy(id = 6, kids = emptyList())
val COMMENT_7 = COMMENT_1.copy(id = 7, kids = emptyList())

class FakeHackerNewsRemoteSource : HackerNewsRemoteSource {

  private val comments =
    listOf(COMMENT_1, COMMENT_2, COMMENT_3, COMMENT_4, COMMENT_5, COMMENT_6, COMMENT_7)

  override suspend fun getStory(storyId: Long): Result<Story> {
    TODO("Not yet implemented")
  }

  override suspend fun getComment(commentId: Long): Result<Comment> {
    return Result.Success(comments.first { comment -> comment.id == commentId })
  }

  override suspend fun getTopStories(): Result<List<Story>> {
    TODO("Not yet implemented")
  }

  override suspend fun getNewStories(): Result<List<Story>> {
    TODO("Not yet implemented")
  }
}
