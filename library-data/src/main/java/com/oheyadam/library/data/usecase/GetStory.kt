package com.oheyadam.library.data.usecase

import com.oheyadam.core.common.network.Result
import com.oheyadam.core.model.Story
import com.oheyadam.library.data.source.HackerNewsRemoteSourceImpl
import javax.inject.Inject

class GetStory @Inject constructor(
  private val remoteSource: HackerNewsRemoteSourceImpl,
) {

  suspend operator fun invoke(storyId: Long): Result<Story> {
    return remoteSource.getStory(storyId)
  }
}
