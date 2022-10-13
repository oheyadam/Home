package com.oheyadam.feature.list.hackernews.usecase

import com.oheyadam.core.common.network.Result
import com.oheyadam.core.model.hackernews.Story
import com.oheyadam.feature.list.hackernews.model.ViewType
import com.oheyadam.feature.list.hackernews.model.ViewType.BestStories
import com.oheyadam.feature.list.hackernews.model.ViewType.NewStories
import com.oheyadam.feature.list.hackernews.model.ViewType.TopStories
import com.oheyadam.library.data.usecase.hackernews.GetNewStories
import com.oheyadam.library.data.usecase.hackernews.GetTopStories
import javax.inject.Inject

class ResolveStories @Inject constructor(
  private val getTopStories: GetTopStories,
  private val getNewStories: GetNewStories,
) {

  suspend operator fun invoke(viewType: ViewType): Result<List<Story>> {
    return when (viewType) {
      is TopStories -> getTopStories()
      is NewStories -> getNewStories()
      is BestStories -> throw IllegalArgumentException()
    }
  }
}
