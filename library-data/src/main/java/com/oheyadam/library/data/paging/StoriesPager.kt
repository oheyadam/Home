package com.oheyadam.library.data.paging

import com.oheyadam.core.common.network.Result
import com.oheyadam.core.common.network.Result.Error
import com.oheyadam.core.common.network.Result.Exception
import com.oheyadam.core.common.network.Result.Success
import com.oheyadam.core.model.Story
import com.oheyadam.library.data.paging.StoriesPager.StoriesType.BEST
import com.oheyadam.library.data.paging.StoriesPager.StoriesType.NEW
import com.oheyadam.library.data.paging.StoriesPager.StoriesType.TOP
import com.oheyadam.library.data.usecase.GetNewStoriesIds
import com.oheyadam.library.data.usecase.GetStory
import com.oheyadam.library.data.usecase.GetTopStoriesIds
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

private const val PAGE_SIZE = 9

internal data class State(
  val topStoriesIds: List<Long> = emptyList(),
  val newStoriesIds: List<Long> = emptyList(),
  val topStories: List<Story> = emptyList(),
  val newStories: List<Story> = emptyList(),
)

@ViewModelScoped
class StoriesPager @Inject constructor(
  private val getTopStoriesIds: GetTopStoriesIds,
  private val getNewStoriesIds: GetNewStoriesIds,
  private val getStory: GetStory,
) {

  private var state = State()

  private val mutableStories = MutableSharedFlow<Result<List<Story>>>()
  val stories = mutableStories.asSharedFlow()

  suspend fun initialize() {
    state = state.copy(topStoriesIds = getTopStoriesIds())
    state = state.copy(newStoriesIds = getNewStoriesIds())
  }

  suspend fun fetchStories(
    type: StoriesType,
    lastIndex: Long?,
  ) {
    when (type) {
      TOP -> fetchTopStories(lastIndex)
      NEW -> fetchNewStories(lastIndex)
      BEST -> {}
    }
  }

  private suspend fun fetchTopStories(lastItem: Long?) {
    val elementIndex = state.topStoriesIds.indexOf(lastItem)
    val cursorIndex = if (elementIndex == -1) {
      state = state.copy(topStories = emptyList())
      0
    } else elementIndex + 1
    val page = state.topStories.toMutableList()
    state.topStoriesIds
      .slice(IntRange(start = cursorIndex, endInclusive = cursorIndex + PAGE_SIZE))
      .forEach { storyId ->
        when (val result = getStory(storyId)) {
          is Success -> page.add(result.data)
          is Error -> mutableStories.emit(result)
          is Exception -> mutableStories.emit(result)
        }
      }
    if (page.isNotEmpty()) {
      state = state.copy(topStories = page)
      mutableStories.emit(Success(page))
    }
  }

  private suspend fun fetchNewStories(lastIndex: Long?) {
  }

  enum class StoriesType {
    TOP,
    NEW,
    BEST
  }
}
