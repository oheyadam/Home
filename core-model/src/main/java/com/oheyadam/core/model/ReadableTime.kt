package com.oheyadam.core.model

sealed class ReadableTime {
  abstract val diff: Int

  object JustNow : ReadableTime() {
    override val diff: Int = Int.MIN_VALUE
  }

  data class Minutes(override val diff: Int) : ReadableTime()
  data class Hours(override val diff: Int) : ReadableTime()
  data class Days(override val diff: Int) : ReadableTime()
  data class Months(override val diff: Int) : ReadableTime()
  data class Years(override val diff: Int) : ReadableTime()
}
