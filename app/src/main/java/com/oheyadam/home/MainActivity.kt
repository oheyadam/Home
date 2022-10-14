package com.oheyadam.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.oheyadam.home.navigation.NavigatorLifecycleObserver
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  @Inject
  lateinit var navigatorLifecycleObserver: NavigatorLifecycleObserver

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    lifecycle.addObserver(navigatorLifecycleObserver)
  }

  override fun onDestroy() {
    super.onDestroy()
    lifecycle.removeObserver(navigatorLifecycleObserver)
  }
}
