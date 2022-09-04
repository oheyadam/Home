package com.oheyadam.library.analytics.di

import com.oheyadam.core.common.initializer.Initializable
import com.oheyadam.library.analytics.timber.TimberInitializer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
abstract class AnalyticsModule {

  @Binds
  @IntoSet
  abstract fun bindTimber(timber: TimberInitializer): Initializable
}
