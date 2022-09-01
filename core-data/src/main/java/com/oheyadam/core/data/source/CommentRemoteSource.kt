package com.oheyadam.core.data.source

import com.oheyadam.core.data.model.toComment
import com.oheyadam.core.model.Comment
import com.oheyadam.core.network.service.HackerNewsService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommentRemoteSource @Inject constructor(
  private val service: HackerNewsService
) {

  suspend fun getComments(ids: List<Int>): List<Comment> {
    return ids.map { id ->
      getComment(id)
    }
  }

  private suspend fun getComment(id: Int): Comment {
    return service.getComment(id).toComment()
  }
}
