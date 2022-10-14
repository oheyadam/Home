package com.oheyadam.home.initializer

import android.app.Application
import com.oheyadam.core.common.initializer.Initializable
import javax.inject.Inject

class AppInitializers @Inject constructor(
  private val initializers: Set<@JvmSuppressWildcards Initializable>,
) {

  fun init(application: Application) {
    initializers.forEach { initializable -> initializable.init(application) }
  }
}
