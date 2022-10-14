package com.oheyadam.library.data.usecase

import com.google.common.truth.Truth.assertThat
import com.oheyadam.core.network.model.ReadableTimeResponse
import com.oheyadam.core.network.moshi.PrettifyInstant
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.toInstant
import org.junit.Test

class PrettifyInstantTest {

  private val clock = object : Clock {
    override fun now(): Instant {
      return "2022-10-14T09:05:48Z".toInstant() // 14/10/2022, 09:05:48AM, UTC
    }
  }

  private val prettifyInstant = PrettifyInstant(clock)

  @Test
  fun `return just now if time diff is less than 1 minute`() {
    val instant = "2022-10-14T09:05:52Z".toInstant() // 14/10/2022, 09:04:52AM, UTC

    assertThat(prettifyInstant(instant)).isEqualTo(ReadableTimeResponse.JustNow)
  }

  @Test
  fun `return diff in minutes if it's more than 1 minute`() {
    val instant = "2022-10-14T09:02:52Z".toInstant() // 14/10/2022, 09:03:52AM, UTC

    assertThat(prettifyInstant(instant)).isEqualTo(ReadableTimeResponse.Minutes(2))
  }

  @Test
  fun `return diff in minutes if it's less than 1 hour`() {
    val instant = "2022-10-14T08:07:48Z".toInstant() // 14/10/2022, 08:07:48AM, UTC

    assertThat(prettifyInstant(instant)).isEqualTo(ReadableTimeResponse.Minutes(58))
  }

  @Test
  fun `return diff in hours if it's 1 hour or more`() {
    val instant = "2022-10-14T08:02:48Z".toInstant() // 14/10/2022, 08:02:48AM, UTC

    assertThat(prettifyInstant(instant)).isEqualTo(ReadableTimeResponse.Hours(1))
  }

  @Test
  fun `return diff in days if it's 1 day or more`() {
    val instant = "2022-10-12T08:02:48Z".toInstant() // 12/10/2022, 08:02:48AM, UTC

    assertThat(prettifyInstant(instant)).isEqualTo(ReadableTimeResponse.Days(2))
  }

  @Test
  fun `return diff in months if it's 1 month or more`() {
    val instant = "2022-08-12T08:02:48Z".toInstant() // 12/08/2022, 08:02:48AM, UTC

    assertThat(prettifyInstant(instant)).isEqualTo(ReadableTimeResponse.Months(2))
  }

  @Test
  fun `return diff in years if it's 1 year or more`() {
    val instant = "2021-10-14T09:05:48Z".toInstant() // 14/10/2021, 09:05:48AM, UTC

    assertThat(prettifyInstant(instant)).isEqualTo(ReadableTimeResponse.Years(1))
  }

  @Test
  fun `doesn't return just now if diff is less than a minute, but more than a year`() {
    val instant = "2020-10-14T09:05:52Z".toInstant() // 14/10/2020, 09:05:52AM, UTC

    assertThat(prettifyInstant(instant)).isEqualTo(ReadableTimeResponse.Years(1))
  }
}
