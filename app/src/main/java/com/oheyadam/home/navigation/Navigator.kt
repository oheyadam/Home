package com.oheyadam.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.oheyadam.feature.list.BreedsFragmentDirections
import com.oheyadam.feature.list.navigation.ListNavigator
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class Navigator @Inject constructor() : ListNavigator {

  private var navController: NavController? = null

  fun bind(controller: NavController) {
    navController = controller
  }

  fun unbind() {
    navController = null
  }

  override fun goToBreedDetail(breedId: Int, breedName: String) {
    val direction = BreedsFragmentDirections
      .actionBreedsFragmentToBreedFragment(breedId, breedName)
    navigate(direction)
  }

  private fun navigate(direction: NavDirections) {
    val controller = requireNotNull(navController)
    controller.navigate(direction)
  }
}
