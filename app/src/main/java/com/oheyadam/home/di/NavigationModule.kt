package com.oheyadam.home.di

import com.oheyadam.feature.list.navigation.ListNavigator
import com.oheyadam.home.navigation.Navigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class NavigationModule {

  @Binds
  abstract fun bindNavigator(navigator: Navigator): ListNavigator
}
