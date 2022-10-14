package com.oheyadam.home.navigation

import androidx.navigation.NavController
import com.oheyadam.feature.list.navigation.ListNavigator
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class Navigator @Inject constructor() : ListNavigator {
  fun bind(controller: NavController) {}
  fun unbind() {}
}
