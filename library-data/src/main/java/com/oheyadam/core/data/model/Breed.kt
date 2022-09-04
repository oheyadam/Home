package com.oheyadam.core.data.model

import com.oheyadam.core.model.Breed
import com.oheyadam.core.network.model.BreedResponse

/**
 * Simplified mapping of both the Height and Weight properties. I'm just defaulting to Metric
 * here, but in the real world, you'd use the user's Locale to figure out which unit type to use
 * */
fun BreedResponse.toBreed(thumbnailUrl: String) = Breed(
  id = id,
  name = name,
  temperament = temperament,
  lifeSpan = lifeSpan,
  weight = weight.metric,
  height = height.metric,
  thumbnailUrl = thumbnailUrl,
  bredFor = bredFor,
  breedGroup = breedGroup,
  countryCode = countryCode,
  description = description,
  history = history,
  origin = origin
)
