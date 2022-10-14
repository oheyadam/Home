package com.oheyadam.home

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.oheyadam.feature.list.StoriesScreen
import com.oheyadam.home.core.design.theme.HomeTheme
import com.oheyadam.home.navigation.NavigatorLifecycleObserver
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  @Inject
  lateinit var navigatorLifecycleObserver: NavigatorLifecycleObserver

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      HomeTheme(dynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        Surface {
          StoriesScreen()
        }
      }
    }
    lifecycle.addObserver(navigatorLifecycleObserver)
  }

  override fun onDestroy() {
    super.onDestroy()
    lifecycle.removeObserver(navigatorLifecycleObserver)
  }
}
