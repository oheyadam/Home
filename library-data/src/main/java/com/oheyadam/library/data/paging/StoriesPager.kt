package com.oheyadam.library.data.paging

import com.oheyadam.core.common.network.Result
import com.oheyadam.core.common.network.Result.Success
import com.oheyadam.core.model.Story
import com.oheyadam.library.data.paging.StoriesPager.StoriesType.BEST
import com.oheyadam.library.data.paging.StoriesPager.StoriesType.NEW
import com.oheyadam.library.data.paging.StoriesPager.StoriesType.TOP
import com.oheyadam.library.data.usecase.GetNewStoriesIds
import com.oheyadam.library.data.usecase.GetStory
import com.oheyadam.library.data.usecase.GetTopStoriesIds
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject

private const val PAGE_SIZE = 15

private data class State(
  val ids: List<Long> = emptyList(),
  val stories: List<Story> = emptyList(),
)

@ViewModelScoped
class StoriesPager @Inject constructor(
  private val getTopStoriesIds: GetTopStoriesIds,
  private val getNewStoriesIds: GetNewStoriesIds,
  private val getStory: GetStory,
) {

  private val state = MutableStateFlow(State())

  private val mutableStories = MutableSharedFlow<Result<List<Story>>>()
  val stories = mutableStories.asSharedFlow()

  suspend fun refresh(type: StoriesType) {
    when (type) {
      TOP -> state.update { s -> s.copy(ids = getTopStoriesIds()) }
      NEW -> state.update { s -> s.copy(ids = getNewStoriesIds()) }
      BEST -> {}
    }
  }

  suspend fun fetch(page: Int) {
    val current = state.value.stories.toMutableList()
    state
      .map { s -> s.ids }
      .collect { result ->
        val ids = result
          .drop((page + 1) * PAGE_SIZE - PAGE_SIZE)
          .take(PAGE_SIZE)
        val stories = coroutineScope { ids.map { id -> async { getStory(id) } }.awaitAll() }
          .filterIsInstance<Success<Story>>()
          .onEach { res -> current.add(res.data) }
          .map { res -> res.data }
        state.update { s -> s.copy(stories = stories) }
        mutableStories.emit(Success(state.value.stories))
      }
  }

  enum class StoriesType {
    TOP,
    NEW,
    BEST
  }
}
