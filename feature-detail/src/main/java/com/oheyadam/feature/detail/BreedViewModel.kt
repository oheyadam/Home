package com.oheyadam.feature.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.oheyadam.core.common.network.onError
import com.oheyadam.core.common.network.onException
import com.oheyadam.core.common.network.onSuccess
import com.oheyadam.core.data.usecase.GetDog
import com.oheyadam.library.analytics.tracker.Trackers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject
import com.oheyadam.core.common.R as CommonR

@HiltViewModel
class BreedViewModel @Inject constructor(
  private val savedStateHandle: SavedStateHandle,
  private val getDog: GetDog,
  private val trackers: Trackers
) : ViewModel() {

  var state by mutableStateOf(BreedState())
    private set

  init {
    getDogDetails()
  }

  fun getDogDetails() {
    val name: String = requireNotNull(savedStateHandle["breedName"])
    val id: Int = requireNotNull(savedStateHandle["breedId"])
    viewModelScope.launch {
      state = state.copy(isLoading = true, errorResId = null)
      getDog(id, name)
        .onSuccess { breed ->
          state = state.copy(
            id = id,
            isLoading = false,
            errorResId = null,
            name = breed.name,
            thumbnailUrl = breed.thumbnailUrl,
            temperament = breed.temperament
          )
        }
        .onError {
          // we could do error-specific filtering based on the error code here
          state = state.copy(isLoading = false, errorResId = CommonR.string.error_generic)
        }
        .onException { throwable ->
          val errorResId = when (throwable) {
            is HttpException, is UnknownHostException -> CommonR.string.error_no_internet_connection
            else -> CommonR.string.error_generic
          }
          state = state.copy(isLoading = false, errorResId = errorResId)
          trackers.error(throwable)
        }
    }
  }
}
