package com.oheyadam.core.network.moshi

import com.oheyadam.core.network.model.hackernews.ReadableTimeResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import kotlinx.datetime.Instant

class ReadableTimeAdapter(
  private val prettifyInstant: PrettifyInstant,
) : JsonAdapter<ReadableTimeResponse>() {

  @Synchronized
  override fun fromJson(reader: JsonReader): ReadableTimeResponse? {
    val instant = Instant.fromEpochSeconds(reader.nextLong())
    return prettifyInstant(instant)
  }

  override fun toJson(
    writer: JsonWriter,
    value: ReadableTimeResponse?,
  ) {
  }
}
