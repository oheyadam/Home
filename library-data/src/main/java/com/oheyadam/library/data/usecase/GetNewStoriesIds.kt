package com.oheyadam.library.data.usecase

import com.oheyadam.core.common.network.Result.Error
import com.oheyadam.core.common.network.Result.Exception
import com.oheyadam.core.common.network.Result.Success
import com.oheyadam.library.data.source.HackerNewsRemoteSourceImpl
import javax.inject.Inject

class GetNewStoriesIds @Inject constructor(
  private val remoteSource: HackerNewsRemoteSourceImpl,
) {

  suspend operator fun invoke(): List<Long> {
    val ids = mutableListOf<Long>()
    when (val result = remoteSource.getNewStoriesIds()) {
      is Success -> ids.addAll(result.data)
      is Error -> {}
      is Exception -> {}
    }
    return ids
  }
}
