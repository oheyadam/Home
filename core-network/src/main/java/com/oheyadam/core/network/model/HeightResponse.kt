package com.oheyadam.core.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HeightResponse(
  @Json(name = "imperial")
  val imperial: String,
  @Json(name = "metric")
  val metric: String
)
