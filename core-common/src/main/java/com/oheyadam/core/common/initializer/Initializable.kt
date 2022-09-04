package com.oheyadam.core.common.initializer

import android.app.Application

interface Initializable {

  fun init(application: Application)
}
