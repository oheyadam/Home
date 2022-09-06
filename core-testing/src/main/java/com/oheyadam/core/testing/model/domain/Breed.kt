package com.oheyadam.core.testing.model

import com.oheyadam.core.model.Breed

val BREED_1 = Breed(
  id = 1,
  bredFor = "Small rodent hunting, lapdog",
  breedGroup = "Toy",
  countryCode = "AG",
  description = "The Affenpinscher is a well-developed, exaggerated bulldog with a broad head and natural drop ears.",
  height = "64 - 69",
  history = "England",
  lifeSpan = "10 - 12 years",
  name = "Affenpinscher",
  origin = "Germany, France",
  thumbnailUrl = "https://cdn2.thedogapi.com/images/33mJ-V3RX.jpg",
  temperament = "Stubborn, Curious, Playful, Adventurous, Active, Fun-loving",
  weight = "23 - 27"
)

val BREED_2 = BREED_1.copy(id = 2)

val BREEDS = listOf(BREED_1, BREED_2)
