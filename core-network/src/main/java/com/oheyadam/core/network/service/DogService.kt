package com.oheyadam.core.network.service

import com.oheyadam.core.common.network.Result
import com.oheyadam.core.network.model.BreedResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DogService {

  @GET(value = "breeds/search")
  suspend fun search(@Query("q") breed: String): Result<List<BreedResponse>>
}
