package com.oheyadam.core.network.moshi

import com.oheyadam.core.network.model.ReadableTimeResponse
import dagger.Reusable
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.TimeZone.Companion
import kotlinx.datetime.daysUntil
import kotlinx.datetime.monthsUntil
import kotlinx.datetime.until
import kotlinx.datetime.yearsUntil
import javax.inject.Inject

@Reusable
class PrettifyInstant @Inject constructor(
  private val clock: Clock,
) {

  operator fun invoke(instant: Instant?): ReadableTimeResponse? {
    if (instant == null) return instant
    val now = clock.now()

    if (instant.minutesUntil(now) <= 1) return ReadableTimeResponse.JustNow

    val years = instant.yearsUntil(now, TimeZone.UTC)
    if (years >= 1) return ReadableTimeResponse.Years(years)

    val months = instant.monthsUntil(now, Companion.UTC)
    if (months >= 1) return ReadableTimeResponse.Months(months)

    val days = instant.daysUntil(now, TimeZone.UTC)
    if (days >= 1) return ReadableTimeResponse.Days(days)

    val hours = instant.hoursUntil(now)
    if (hours >= 1) return ReadableTimeResponse.Hours(hours)

    val minutes = instant.minutesUntil(now)
    if (minutes >= 1) return ReadableTimeResponse.Minutes(minutes)

    throw IllegalArgumentException()
  }

  private fun Instant.minutesUntil(
    other: Instant,
    timeZone: TimeZone = TimeZone.UTC,
  ): Int = until(other, DateTimeUnit.MINUTE, timeZone).toInt()

  private fun Instant.hoursUntil(
    other: Instant,
    timeZone: TimeZone = TimeZone.UTC,
  ): Int = until(other, DateTimeUnit.HOUR, timeZone).toInt()
}
