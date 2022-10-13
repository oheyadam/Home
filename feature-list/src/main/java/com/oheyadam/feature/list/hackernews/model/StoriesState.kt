package com.oheyadam.feature.list.hackernews.model

import androidx.annotation.StringRes
import com.oheyadam.core.design.R
import com.oheyadam.feature.list.hackernews.model.ViewType.BestStories
import com.oheyadam.feature.list.hackernews.model.ViewType.NewStories
import com.oheyadam.feature.list.hackernews.model.ViewType.TopStories
import com.oheyadam.home.core.design.component.ChipMetadata

data class StoriesState(
  val isLoading: Boolean = false,
  val storyItems: List<StoryItem> = emptyList(),
  val viewTypes: List<ViewType> = listOf(TopStories(), NewStories(), BestStories()),
  val selectedViewType: ViewType = TopStories(),
  @StringRes val errorResId: Int? = null,
) {

  fun resolveViewType(id: Int): ViewType {
    return viewTypes.first { viewType -> viewType.id == id }
  }
}

sealed class ViewType {
  abstract val id: Int
  abstract val labelResId: Int

  class TopStories(
    override val id: Int = 1,
    @StringRes override val labelResId: Int = R.string.chip_top_stories,
  ) : ViewType()

  class NewStories(
    override val id: Int = 2,
    @StringRes override val labelResId: Int = R.string.chip_new_stories,
  ) : ViewType()

  class BestStories(
    override val id: Int = 3,
    @StringRes override val labelResId: Int = R.string.chip_best_stories,
  ) : ViewType()

  fun toChipMetadata() = ChipMetadata(id = id, labelResId = labelResId)
}

fun List<ViewType>.toChipsMetadata() = map { viewType -> viewType.toChipMetadata() }
