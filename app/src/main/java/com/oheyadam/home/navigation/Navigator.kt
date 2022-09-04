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

  override fun goToBreedDetail(breedId: Int) {
    val direction = BreedsFragmentDirections.actionBreedsFragmentToBreedFragment(breedId)
    safelyNavigate(direction)
  }

  private fun safelyNavigate(directions: NavDirections) {
    val controller = navController ?: return
    controller.navigate(directions)
  }
}
