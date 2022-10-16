package com.oheyadam.core.network.moshi

import com.oheyadam.core.network.model.ReadableTimeResponse
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson
import kotlinx.datetime.Instant

class ReadableTimeAdapter(
  private val prettifyInstant: PrettifyInstant,
) : JsonAdapter<ReadableTimeResponse>() {

  @Synchronized
  @FromJson
  override fun fromJson(reader: JsonReader): ReadableTimeResponse? {
    if (reader.peek() == null) return reader.nextNull()
    val instant = Instant.fromEpochSeconds(reader.nextLong())
    return prettifyInstant(instant)
  }

  @ToJson
  override fun toJson(
    writer: JsonWriter,
    value: ReadableTimeResponse?,
  ) {
  }
}
