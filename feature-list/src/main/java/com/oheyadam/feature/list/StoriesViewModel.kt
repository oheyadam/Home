package com.oheyadam.feature.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oheyadam.core.common.coroutines.cachedIn
import com.oheyadam.core.common.network.onError
import com.oheyadam.core.common.network.onException
import com.oheyadam.core.common.network.onSuccess
import com.oheyadam.feature.list.model.StoriesEvent
import com.oheyadam.feature.list.model.StoriesEvent.Load
import com.oheyadam.feature.list.model.StoriesEvent.LoadMore
import com.oheyadam.feature.list.model.StoriesEvent.OpenItem
import com.oheyadam.feature.list.model.StoriesEvent.OpenSettings
import com.oheyadam.feature.list.model.StoriesEvent.UpdateViewType
import com.oheyadam.feature.list.model.StoriesState
import com.oheyadam.feature.list.model.ViewType.BestStories
import com.oheyadam.feature.list.model.ViewType.NewStories
import com.oheyadam.feature.list.model.ViewType.TopStories
import com.oheyadam.feature.list.model.toStoryItems
import com.oheyadam.library.analytics.tracker.Trackers
import com.oheyadam.library.data.paging.StoriesPager
import com.oheyadam.library.data.paging.StoriesPager.StoriesType.BEST
import com.oheyadam.library.data.paging.StoriesPager.StoriesType.NEW
import com.oheyadam.library.data.paging.StoriesPager.StoriesType.TOP
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val VIEW_TYPE = "VIEW_TYPE"

@HiltViewModel
class StoriesViewModel @Inject constructor(
  private val savedStateHandle: SavedStateHandle,
  private val storiesPager: StoriesPager,
  private val trackers: Trackers,
) : ViewModel() {

  private var job: Job? = null

  private val internalState = MutableStateFlow(StoriesState())
  val state = internalState.asStateFlow()

  init {
    val selectedViewType: Int? = savedStateHandle[VIEW_TYPE]
    if (selectedViewType != null) {
      consume(UpdateViewType(selectedViewType))
    } else {
      consume(Load(internalState.value.selectedViewType.id))
    }
    observeStories()
  }

  fun consume(event: StoriesEvent) {
    when (event) {
      is Load -> load(event.viewTypeId)
      is OpenItem -> {}
      is UpdateViewType -> updateViewType(event)
      OpenSettings -> {}
      LoadMore -> {
        val page = internalState.value.page
        fetchStories(page)
      }
    }
  }

  private fun observeStories() {
    viewModelScope.launch {
      storiesPager.stories
        .cachedIn(viewModelScope)
        .collect { result ->
          result
            .onSuccess { stories ->
              internalState.update { s ->
                s.copy(isLoading = false, storyItems = stories.toStoryItems())
              }
            }
            .onError { internalState.update { s -> s.copy(isLoading = false) } }
            .onException { throwable ->
              trackers.error(throwable)
              internalState.update { s -> s.copy(isLoading = false) }
            }
        }
    }
  }

  private fun updateViewType(event: UpdateViewType) {
    val viewTypeId = event.viewTypeId
    internalState.update { s -> s.copy(selectedViewType = s.resolveViewType(viewTypeId)) }
    consume(Load(viewTypeId))
  }

  private fun load(viewTypeId: Int) {
    viewModelScope.launch {
      when (internalState.value.resolveViewType(viewTypeId)) {
        is TopStories -> storiesPager.refresh(TOP)
        is NewStories -> storiesPager.refresh(NEW)
        is BestStories -> storiesPager.refresh(BEST)
      }
      viewModelScope.launch {
        delay(300)
      }
      fetchStories(internalState.value.page)
    }
  }

  private fun fetchStories(page: Int) {
    job?.cancel()
    job = viewModelScope.launch {
      internalState.update { s -> s.copy(isLoading = true, page = page + 1) }
      storiesPager.fetch(page)
    }
  }

  override fun onCleared() {
    savedStateHandle[VIEW_TYPE] = internalState.value.selectedViewType.id
    super.onCleared()
  }
}
