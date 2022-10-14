package com.oheyadam.library.data.model

import com.oheyadam.core.model.ReadableTime
import com.oheyadam.core.network.model.ReadableTimeResponse
import com.oheyadam.core.network.model.ReadableTimeResponse.Days
import com.oheyadam.core.network.model.ReadableTimeResponse.Hours
import com.oheyadam.core.network.model.ReadableTimeResponse.JustNow
import com.oheyadam.core.network.model.ReadableTimeResponse.Minutes
import com.oheyadam.core.network.model.ReadableTimeResponse.Months
import com.oheyadam.core.network.model.ReadableTimeResponse.Years

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
