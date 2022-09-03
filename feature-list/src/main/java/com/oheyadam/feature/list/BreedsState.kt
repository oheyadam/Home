package com.oheyadam.feature.list

import androidx.annotation.StringRes
import com.oheyadam.feature.list.model.BreedItem

data class BreedsState(
  val isLoading: Boolean = false,
  @StringRes val errorResId: Int? = null,
  val query: String = "",
  val result: List<BreedItem> = listOf(),
  val selectedBreedId: Int? = null
)
