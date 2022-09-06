package com.oheyadam.core.testing.model.remote

import com.oheyadam.core.network.model.BreedResponse
import com.oheyadam.core.network.model.HeightResponse
import com.oheyadam.core.network.model.ImageResponse
import com.oheyadam.core.network.model.WeightResponse

val HEIGHT_RESPONSE = HeightResponse(
  imperial = "25 - 27",
  metric = "64 - 69"
)

val WEIGHT_RESPONSE = WeightResponse(
  imperial = "50 - 60",
  metric = "23 - 27"
)

val BREED_RESPONSE_1 = BreedResponse(
  id = 1,
  bredFor = "Small rodent hunting, lapdog",
  breedGroup = "Toy",
  countryCode = "AG",
  description = "The Affenpinscher is a well-developed, exaggerated bulldog with a broad head and natural drop ears.",
  height = HEIGHT_RESPONSE,
  history = "England",
  lifeSpan = "10 - 12 years",
  name = "Affenpinscher",
  origin = "Germany, France",
  referenceImageId = "BJa4kxc4X",
  temperament = "Stubborn, Curious, Playful, Adventurous, Active, Fun-loving",
  weight = WEIGHT_RESPONSE
)

val BREED_RESPONSE_2 = BREED_RESPONSE_1.copy(id = 2)

val BREEDS_RESPONSES = listOf(BREED_RESPONSE_1, BREED_RESPONSE_2)

val IMAGE_RESPONSE = ImageResponse(
  id = "BJa4kxc4X",
  url = "https://cdn2.thedogapi.com/images/33mJ-V3RX.jpg"
)
