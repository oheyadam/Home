package com.oheyadam.feature.list.usecase

import com.oheyadam.core.common.network.Result
import com.oheyadam.core.model.Story
import com.oheyadam.feature.list.model.ViewType
import com.oheyadam.feature.list.model.ViewType.BestStories
import com.oheyadam.feature.list.model.ViewType.NewStories
import com.oheyadam.feature.list.model.ViewType.TopStories
import com.oheyadam.library.data.usecase.GetNewStories
import com.oheyadam.library.data.usecase.GetTopStories
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
