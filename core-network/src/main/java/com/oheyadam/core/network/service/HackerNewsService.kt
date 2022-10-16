package com.oheyadam.core.network.service

import com.oheyadam.core.common.network.Result
import com.oheyadam.core.network.model.ItemResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HackerNewsService {

  @GET(value = "item/{item-id}.json")
  suspend fun getItem(@Path("item-id") itemId: Long): Result<ItemResponse>

  @GET(value = "topstories.json")
  suspend fun getTopStories(): Result<List<Long>>

  @GET(value = "newstories.json")
  suspend fun getNewStories(): Result<List<Long>>
}
