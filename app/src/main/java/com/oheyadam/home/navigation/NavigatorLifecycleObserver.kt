package com.oheyadam.home.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import com.oheyadam.home.R
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class NavigatorLifecycleObserver @Inject constructor(
  private val navigator: Navigator
) : DefaultLifecycleObserver {

  override fun onStart(owner: LifecycleOwner) {
    super.onStart(owner)
    val controller = (owner as AppCompatActivity).findNavController(R.id.nav_host_fragment)
    navigator.bind(controller)
  }

  override fun onStop(owner: LifecycleOwner) {
    super.onStop(owner)
    navigator.unbind()
  }
}
