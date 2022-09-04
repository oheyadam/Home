package com.oheyadam.feature.list.model

import com.oheyadam.core.model.Breed

data class BreedItem(
  val id: Int,
  val thumbnailUrl: String?,
  val name: String
)

fun Breed.toBreedItem() = BreedItem(
  id = id,
  thumbnailUrl = thumbnailUrl,
  name = name
)

fun List<Breed>.toBreedItems() = map { breed -> breed.toBreedItem() }
