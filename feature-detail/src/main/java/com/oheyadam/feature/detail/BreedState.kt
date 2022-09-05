package com.oheyadam.feature.detail

import androidx.annotation.StringRes

data class BreedState(
  val isLoading: Boolean = false,
  val id: Int = -1,
  val name: String? = null,
  val thumbnailUrl: String? = null,
  val temperament: String? = null,
  @StringRes val errorResId: Int? = null,
)
