package com.oheyadam.library.data.usecase.hackernews

import com.oheyadam.core.common.network.Result
import com.oheyadam.core.model.hackernews.Comment
import com.oheyadam.library.data.source.hackernews.HackerNewsRemoteSource
import javax.inject.Inject

class GetComment @Inject constructor(
	private val remoteSource: HackerNewsRemoteSource,
) {

  suspend operator fun invoke(commentId: Long): Result<Comment> {
    return remoteSource.getComment(commentId)
  }
}
