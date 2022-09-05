package com.oheyadam.feature.list

import androidx.annotation.StringRes
import com.oheyadam.feature.list.model.BreedItem

data class BreedsState(
  val isLoading: Boolean = false,
  val result: List<BreedItem> = listOf(),
  val query: String? = null,
  val selectedBreedId: Int? = null,
  val selectedBreedName: String? = null,
  @StringRes val errorResId: Int? = null
) {

  val isClearButtonVisible: Boolean = query?.isNotBlank() == true
  val isEmptyStateVisible: Boolean = !isLoading && !query.isNullOrBlank() && result.isEmpty()
}
