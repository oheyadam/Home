package com.oheyadam.home

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class HomeApplication : Application() {

  override fun onCreate() {
    super.onCreate()
    Timber.plant()
  }
}
