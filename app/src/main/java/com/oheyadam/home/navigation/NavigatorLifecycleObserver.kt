package com.oheyadam.home.navigation

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class NavigatorLifecycleObserver @Inject constructor() : DefaultLifecycleObserver {

  override fun onStart(owner: LifecycleOwner) {
    super.onStart(owner)
  }

  override fun onStop(owner: LifecycleOwner) {
    super.onStop(owner)
  }
}
