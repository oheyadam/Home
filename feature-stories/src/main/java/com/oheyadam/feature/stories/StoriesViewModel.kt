package com.oheyadam.feature.stories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oheyadam.core.data.source.StoryRemoteSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoriesViewModel @Inject constructor(
  private val storyRemoteSource: StoryRemoteSource
) : ViewModel() {

  var state = MutableStateFlow(StoriesState())
    private set

  init {
    viewModelScope.launch {
      state.update { s -> s.copy(isLoading = true) }
      try {
        val stories = storyRemoteSource.getTopStories()
      } catch (throwable: Throwable) {

      }
    }
  }
}
