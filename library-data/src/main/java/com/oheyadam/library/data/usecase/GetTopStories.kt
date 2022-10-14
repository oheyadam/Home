package com.oheyadam.library.data.usecase

import com.oheyadam.core.common.network.Result
import com.oheyadam.core.model.Story
import com.oheyadam.library.data.source.HackerNewsRemoteSourceImpl
import javax.inject.Inject

class GetTopStories @Inject constructor(
  private val remoteSource: HackerNewsRemoteSourceImpl,
) {

  suspend operator fun invoke(): Result<List<Story>> {
    return remoteSource.getTopStories()
  }
}
