package com.oheyadam.library.analytics.timber

import com.oheyadam.library.analytics.tracker.Tracker
import timber.log.Timber
import javax.inject.Inject

class TimberTracker @Inject constructor() : Tracker {

  override fun error(throwable: Throwable) {
    Timber.e(throwable)
  }
}
