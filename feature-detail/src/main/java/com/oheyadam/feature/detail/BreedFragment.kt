package com.oheyadam.feature.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreedFragment : Fragment() {

  private val viewModel: BreedViewModel by viewModels()

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

      }
    }
    return view
  }
}
