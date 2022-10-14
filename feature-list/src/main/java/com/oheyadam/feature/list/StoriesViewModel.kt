package com.oheyadam.feature.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oheyadam.core.common.network.onError
import com.oheyadam.core.common.network.onException
import com.oheyadam.core.common.network.onSuccess
import com.oheyadam.feature.list.model.StoriesState
import com.oheyadam.feature.list.model.toStoryItems
import com.oheyadam.feature.list.usecase.ResolveStories
import com.oheyadam.library.analytics.tracker.Trackers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val VIEW_TYPE = "VIEW_TYPE"

@HiltViewModel
class StoriesViewModel @Inject constructor(
  private val savedStateHandle: SavedStateHandle,
  private val resolveStories: ResolveStories,
  private val trackers: Trackers,
) : ViewModel() {

  private var job: Job? = null

  private val internalState = MutableStateFlow(StoriesState())
  val state = internalState.asStateFlow()

  init {
    val selectedViewType: Int? = savedStateHandle[VIEW_TYPE]
    if (selectedViewType != null) {
      onViewTypeUpdated(selectedViewType)
    } else {
      getStories()
    }
  }

  fun onViewTypeUpdated(id: Int) {
    savedStateHandle[VIEW_TYPE] = id
    internalState.update { s -> s.copy(selectedViewType = s.resolveViewType(id)) }
    getStories()
  }

  fun getStories() {
    job?.cancel()
    job = viewModelScope.launch {
      internalState.update { s -> s.copy(isLoading = true) }
      resolveStories(internalState.value.selectedViewType)
        .onSuccess { stories ->
          internalState.update { s ->
            s.copy(
              isLoading = false,
              storyItems = stories.toStoryItems()
            )
          }
        }
        .onError { }
        .onException { }
    }
  }
}
