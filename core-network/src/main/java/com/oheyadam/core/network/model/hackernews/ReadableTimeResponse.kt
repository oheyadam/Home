package com.oheyadam.core.network.model.hackernews

sealed class ReadableTimeResponse {
  abstract val diff: Int

  object JustNow : ReadableTimeResponse() {
    override val diff: Int = Int.MIN_VALUE
  }

  data class Minutes(override val diff: Int) : ReadableTimeResponse()
  data class Hours(override val diff: Int) : ReadableTimeResponse()
  data class Days(override val diff: Int) : ReadableTimeResponse()
  data class Months(override val diff: Int) : ReadableTimeResponse()
  data class Years(override val diff: Int) : ReadableTimeResponse()
}
