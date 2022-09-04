package com.oheyadam.library.analytics.tracker

import javax.inject.Inject

class Trackers @Inject constructor(
  private val trackers: Set<@JvmSuppressWildcards Tracker>
) {

  fun error(t: Throwable) {
    trackers.forEach { tracker -> tracker.error(t) }
  }
}
