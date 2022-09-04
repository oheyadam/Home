package com.oheyadam.feature.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class BreedViewModel @Inject constructor(
  private val savedStateHandle: SavedStateHandle
) : ViewModel() {

  private val internalState = MutableStateFlow(BreedState())
  val state = internalState.asStateFlow()
  
}
