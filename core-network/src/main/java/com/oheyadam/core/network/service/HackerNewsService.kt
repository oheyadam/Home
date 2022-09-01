package com.oheyadam.core.network.service

import com.oheyadam.core.network.model.CommentResponse
import com.oheyadam.core.network.model.StoryResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HackerNewsService {

  @GET(value = "topstories.json")
  suspend fun getTopStories(): List<StoryResponse>

  @GET(value = "item/{item-id}.json")
  suspend fun getComment(@Path("item-id") id: Int): CommentResponse
}
