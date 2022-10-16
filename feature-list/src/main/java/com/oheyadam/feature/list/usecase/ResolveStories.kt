package com.oheyadam.feature.list.usecase

import com.oheyadam.feature.list.model.ViewType
import com.oheyadam.feature.list.model.ViewType.BestStories
import com.oheyadam.feature.list.model.ViewType.NewStories
import com.oheyadam.feature.list.model.ViewType.TopStories
import com.oheyadam.library.data.paging.StoriesPager
import com.oheyadam.library.data.paging.StoriesPager.StoriesType.NEW
import com.oheyadam.library.data.paging.StoriesPager.StoriesType.TOP
import javax.inject.Inject

class ResolveStories @Inject constructor(
  private val storiesPager: StoriesPager,
) {

  suspend operator fun invoke(
    viewType: ViewType,
    lastId: Long?,
  ) {
    return when (viewType) {
      is TopStories -> storiesPager.fetchStories(TOP, lastId)
      is NewStories -> storiesPager.fetchStories(NEW, lastId)
      is BestStories -> throw IllegalArgumentException()
    }
  }
}
