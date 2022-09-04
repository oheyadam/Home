package com.oheyadam.core.data.source

import com.oheyadam.core.common.network.Result
import com.oheyadam.core.common.network.Result.*
import com.oheyadam.core.data.model.toBreed
import com.oheyadam.core.model.Breed
import com.oheyadam.core.network.service.DogService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BreedRemoteSource @Inject constructor(
  private val dogService: DogService
) {

  suspend fun search(query: String): Result<List<Breed>> {
    return when (val response = dogService.search(query)) {
      is Success -> {
        val data = response.data.map { breedResponse ->
          val referenceId = breedResponse.referenceImageId
          if (referenceId.isNullOrBlank()) return@map breedResponse.toBreed("")
          val imageUrl = getImageUrl(referenceId)
          breedResponse.toBreed(imageUrl)
        }
        Success(data)
      }
      is Error -> Error(response.code)
      is Exception -> Exception(response.throwable)
    }
  }

  private suspend fun getImageUrl(referenceId: String): String {
    return when (val response = dogService.getImage(referenceId)) {
      is Success -> response.data.url
      /*
        * These errors should be tracked, but either way, we can just pass along an empty
        * String which can be replaced by a placeholder image downstream.
        * Not propagating this error up to the Search function is a good idea imo because showing
        * a placeholder image along with some textual data about the breed beats throwing
        * an exception even though we technically have the textual Breed data
        * */
      is Error, is Exception -> ""
    }
  }
}
