package com.oheyadam.core.data.source

import com.oheyadam.core.common.network.Result
import com.oheyadam.core.common.network.Result.*
import com.oheyadam.core.data.model.toBreeds
import com.oheyadam.core.model.Breed
import com.oheyadam.core.network.service.DogService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BreedRemoteSource @Inject constructor(
  private val dogService: DogService
) {

  suspend fun search(breed: String): Result<List<Breed>> {
    return when (val response = dogService.search(breed)) {
      is Success -> Success(response.data.toBreeds())
      is Error -> Error(response.code)
      is Exception -> Exception(response.throwable)
    }
  }
}
