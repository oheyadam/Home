package com.oheyadam.feature.list.model

import com.oheyadam.core.testing.model.BREED_1
import com.oheyadam.core.testing.model.BREED_2

val BREED_ITEM_1 = BreedItem(
  id = BREED_1.id,
  thumbnailUrl = BREED_1.thumbnailUrl,
  name = BREED_1.name
)

val BREED_ITEM_2 = BreedItem(
  id = BREED_2.id,
  thumbnailUrl = BREED_2.thumbnailUrl,
  name = BREED_2.name
)

val BREED_ITEMS = listOf(BREED_ITEM_1, BREED_ITEM_2)
