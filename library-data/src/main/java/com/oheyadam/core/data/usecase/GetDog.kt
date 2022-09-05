package com.oheyadam.core.data.usecase

import com.oheyadam.core.common.network.Result
import com.oheyadam.core.data.source.BreedRemoteSource
import com.oheyadam.core.model.Breed
import javax.inject.Inject

class GetDog @Inject constructor(
  private val breedRemoteSource: BreedRemoteSource
) {

  suspend operator fun invoke(breedId: Int, breedName: String): Result<Breed> {
    return breedRemoteSource.getBreed(breedId, breedName)
  }
}
