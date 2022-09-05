package com.oheyadam.feature.list

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import com.oheyadam.core.common.ui.recyclerview.ListItemDecoration
import com.oheyadam.core.common.ui.viewbinding.viewBinding
import com.oheyadam.feature.list.databinding.FragmentBreedsBinding
import com.oheyadam.feature.list.navigation.ListNavigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.oheyadam.core.common.R as CommonR

@AndroidEntryPoint
class BreedsFragment : Fragment(R.layout.fragment_breeds) {

  @Inject
  lateinit var navigator: ListNavigator

  private val viewModel: BreedsViewModel by viewModels()
  private val binding: FragmentBreedsBinding by viewBinding()
  private val adapter = BreedAdapter { breed ->
    viewModel.selectedBreed(breed.id, breed.name)
  }
  private lateinit var itemDecoration: ListItemDecoration

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
      buttonClear.isVisible = state.isClearButtonVisible
      edittextSearchField.setText(state.query)
      if (state.isEmptyStateVisible) showSnackbar(CommonR.string.warning_no_matching_results)
      adapter.submitList(state.result)
      state.selectedBreedId?.let { selectedBreedId ->
        navigator.goToBreedDetail(selectedBreedId, state.selectedBreedName)
        viewModel.breedSelected()
      }
      state.errorResId?.let { errorResId ->
        showSnackbar(errorResId, refreshable = true) {
          viewModel.search(state.query)
          viewModel.errorMessageShown()
        }
      }
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    binding.listDogs.removeItemDecoration(itemDecoration)
  }

  private fun initializeUiElements() {
    initializeRecyclerView()
    with(binding) {
      buttonClear.setOnClickListener {
        viewModel.clear()
      }
      edittextSearchField.setOnEditorActionListener { textView, actionId, _ ->
        return@setOnEditorActionListener when (actionId) {
          EditorInfo.IME_ACTION_SEARCH -> {
            viewModel.search(textView.text.toString())
            hideSoftKeyboard(edittextSearchField)
            true
          }
          else -> false
        }
      }
    }
  }

  private fun initializeRecyclerView() {
    val itemMargins = resources.getDimensionPixelSize(CommonR.dimen.spacing_default)
    itemDecoration = ListItemDecoration(itemMargins)
    with(binding) {
      listDogs.adapter = adapter
      listDogs.addItemDecoration(itemDecoration)
    }
  }

  private fun hideSoftKeyboard(view: View) {
    if (view.requestFocus()) {
      view.clearFocus()
      val imm = requireActivity()
        .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.RESULT_SHOWN)
    }
  }

  private fun showSnackbar(
    messageResId: Int, refreshable: Boolean = false, refreshAction: () -> Unit = {}
  ) {
    val length = if (refreshable) Snackbar.LENGTH_INDEFINITE else Snackbar.LENGTH_LONG
    Snackbar.make(binding.root, messageResId, length).apply {
      if (refreshable) {
        setAction(CommonR.string.action_refresh) {
          refreshAction()
        }
      }
    }.show()
  }
}
