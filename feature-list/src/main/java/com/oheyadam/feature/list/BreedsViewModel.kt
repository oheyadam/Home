package com.oheyadam.feature.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.oheyadam.core.common.network.onError
import com.oheyadam.core.common.network.onException
import com.oheyadam.core.common.network.onSuccess
import com.oheyadam.core.data.usecase.SearchBreed
import com.oheyadam.feature.list.model.toBreedItems
import com.oheyadam.library.analytics.tracker.Trackers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class BreedsViewModel @Inject constructor(
  private val searchBreed: SearchBreed,
  private val trackers: Trackers
) : ViewModel() {

  private val internalState = MutableStateFlow(BreedsState())
  val state = internalState.asStateFlow()

  private var job: Job? = null

  fun search(breed: String) {
    if (breed.isEmpty()) return
    job?.cancel()
    job = viewModelScope.launch {
      internalState.update { s -> s.copy(isLoading = true, errorResId = null, query = breed) }
      searchBreed(breed)
        .onSuccess { breeds ->
          internalState.update { s ->
            s.copy(isLoading = false, errorResId = null, result = breeds.toBreedItems())
          }
        }
        .onError { _ ->
          // we could do error-specific filtering based on the error code here
          internalState.update { s ->
            s.copy(isLoading = false, errorResId = R.string.error_generic)
          }
        }
        .onException { throwable ->
          val errorResId = when (throwable) {
            is HttpException, is UnknownHostException -> R.string.error_no_internet_connection
            else -> R.string.error_generic
          }
          internalState.update { s -> s.copy(isLoading = false, errorResId = errorResId) }
          trackers.error(throwable)
        }
    }
  }

  fun selectedBreed(id: Int) {
    internalState.update { s -> s.copy(selectedBreedId = id) }
  }

  fun breedSelected() {
    internalState.update { s -> s.copy(selectedBreedId = null) }
  }

  fun errorMessageShown() {
    internalState.update { s -> s.copy(errorResId = null) }
  }

  fun clear() {
    job?.cancel()
    internalState.update { s -> s.copy(isLoading = false, query = "", result = emptyList()) }
  }
}
