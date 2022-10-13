package com.oheyadam.library.data.usecase.hackernews

import com.oheyadam.core.common.network.Result
import com.oheyadam.core.model.hackernews.Story
import com.oheyadam.library.data.source.hackernews.HackerNewsRemoteSourceImpl
import javax.inject.Inject

class GetTopStories @Inject constructor(
  private val remoteSource: HackerNewsRemoteSourceImpl,
) {

  suspend operator fun invoke(): Result<List<Story>> {
    return remoteSource.getTopStories()
  }
}
