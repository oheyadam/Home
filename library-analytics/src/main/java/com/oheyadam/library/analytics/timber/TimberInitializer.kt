package com.oheyadam.library.analytics.timber

import android.app.Application
import com.oheyadam.core.common.initializer.Initializable
import com.oheyadam.library.analytics.BuildConfig
import timber.log.Timber
import javax.inject.Inject

class TimberInitializer @Inject constructor() : Initializable {

  override fun init(application: Application) {
    if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
  }
}
