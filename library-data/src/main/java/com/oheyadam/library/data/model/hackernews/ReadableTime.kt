package com.oheyadam.library.data.model.hackernews

import com.oheyadam.core.model.hackernews.ReadableTime
import com.oheyadam.core.network.model.hackernews.ReadableTimeResponse
import com.oheyadam.core.network.model.hackernews.ReadableTimeResponse.Days
import com.oheyadam.core.network.model.hackernews.ReadableTimeResponse.Hours
import com.oheyadam.core.network.model.hackernews.ReadableTimeResponse.JustNow
import com.oheyadam.core.network.model.hackernews.ReadableTimeResponse.Minutes
import com.oheyadam.core.network.model.hackernews.ReadableTimeResponse.Months
import com.oheyadam.core.network.model.hackernews.ReadableTimeResponse.Years

fun ReadableTimeResponse.toReadableTime(): ReadableTime {
  return when (this) {
    JustNow -> ReadableTime.JustNow
    is Days -> ReadableTime.Days(diff)
    is Hours -> ReadableTime.Hours(diff)
    is Minutes -> ReadableTime.Minutes(diff)
    is Months -> ReadableTime.Months(diff)
    is Years -> ReadableTime.Years(diff)
  }
}
