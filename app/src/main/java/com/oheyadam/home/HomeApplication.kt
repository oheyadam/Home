package com.oheyadam.home

import android.app.Application
import com.oheyadam.home.initializer.AppInitializers
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class HomeApplication : Application() {

  @Inject
  lateinit var initializers: AppInitializers

  override fun onCreate() {
    super.onCreate()
    initializers.init(this)
  }
}
