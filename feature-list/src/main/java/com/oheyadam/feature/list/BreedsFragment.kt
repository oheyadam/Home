package com.oheyadam.feature.list

import android.os.Bundle
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import com.oheyadam.core.common.ui.recyclerview.ListItemDecoration
import com.oheyadam.core.common.ui.viewbinding.viewBinding
import com.oheyadam.feature.stories.R
import com.oheyadam.feature.stories.databinding.FragmentBreedsBinding
import kotlinx.coroutines.launch
import com.oheyadam.core.common.R as CommonR

class BreedsFragment : Fragment() {

  private val viewModel: BreedsViewModel by viewModels()
  private val binding: FragmentBreedsBinding by viewBinding()
  private val adapter = BreedAdapter { breed ->
    viewModel.selectedBreed(breed.id)
  }
  private lateinit var itemDecoration: ListItemDecoration
  private lateinit var textWatcher: TextWatcher

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View = binding.root

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initializeUiElements()
    viewLifecycleOwner.lifecycleScope.launch {
      repeatOnLifecycle(Lifecycle.State.STARTED) {
        viewModel.state.collect { state ->
          render(state)
        }
      }
    }
  }

  private fun render(state: BreedsState) {
    with(binding) {
      progressView.isVisible = state.isLoading
      adapter.submitList(state.result)
      state.selectedBreedId?.let { selectedBreedId ->
        // navigate to detail view
        viewModel.breedSelected()
      }
      state.errorResId?.let { errorResId ->
        showSnackbar(errorResId, state.query)
      }
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    with(binding) {
      edittextSearchField.removeTextChangedListener(textWatcher)
      listDogs.removeItemDecoration(itemDecoration)
    }
  }

  private fun initializeUiElements() {
    val itemMargins = resources.getDimensionPixelSize(CommonR.dimen.spacing_default)
    with(binding) {
      buttonCancel.setOnClickListener {
        edittextSearchField.text.clear()
      }
      listDogs.adapter = adapter
      listDogs.addItemDecoration(itemDecoration)
      itemDecoration = ListItemDecoration(itemMargins)
      textWatcher = edittextSearchField.doOnTextChanged { text, _, _, _ ->
        viewModel.search(text.toString())
      }
    }
  }

  private fun showSnackbar(errorResId: Int, query: String) {
    Snackbar.make(binding.root, errorResId, Snackbar.LENGTH_LONG)
      .setAction(R.string.action_refresh) {
        viewModel.search(query)
        viewModel.errorMessageShown()
      }
      .show()
  }

  companion object {
    fun newInstance() = BreedsFragment()
  }
}
