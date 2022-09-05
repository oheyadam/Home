package com.oheyadam.feature.list

import androidx.annotation.StringRes
import com.oheyadam.feature.list.model.BreedItem

data class BreedsState(
  val isLoading: Boolean = false,
  val query: String = "",
  val result: List<BreedItem> = listOf(),
  val selectedBreedId: Int? = null,
  val selectedBreedName: String = "",
  @StringRes val errorResId: Int? = null
) {

  val isClearButtonVisible = query.isNotBlank()
  val isEmptyStateVisible = !isLoading && query.isNotEmpty() && result.isEmpty()
}
