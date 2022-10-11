package com.oheyadam.feature.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreedFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val view = ComposeView(requireContext())
    // By default, Compose disposes of the Composition whenever the view becomes detached from a window,
    // but for Fragments, you want to follow the View lifecycle
    view.setViewCompositionStrategy(DisposeOnViewTreeLifecycleDestroyed)
    view.setContent {
      MaterialTheme {
        DetailScreen()
      }
    }
    return view
  }
}

@Preview
@Composable
fun Preview() {
  DetailScreen()
}
