package com.oheyadam.feature.detail

import androidx.annotation.StringRes
import com.oheyadam.feature.detail.model.BreedDetail

data class BreedState(
  val isLoading: Boolean = false,
  @StringRes val errorResId: Int? = null,
  val query: String = "",
  val detail: BreedDetail = BreedDetail.initial(),
)
