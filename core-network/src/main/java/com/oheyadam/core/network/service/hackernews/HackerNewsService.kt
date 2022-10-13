package com.oheyadam.core.network.service.hackernews

import com.oheyadam.core.common.network.Result
import com.oheyadam.core.network.model.hackernews.ItemResponse
import com.oheyadam.core.network.model.hackernews.StoryResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HackerNewsService {

	@GET(value = "item/{item-id}.json")
	suspend fun getItem(@Path("item-id") itemId: Long): Result<ItemResponse>

	@GET(value = "topstories.json")
	suspend fun getTopStories(): Result<List<StoryResponse>>

	@GET(value = "newstories.json")
	suspend fun getNewStories(): Result<List<StoryResponse>>
}
