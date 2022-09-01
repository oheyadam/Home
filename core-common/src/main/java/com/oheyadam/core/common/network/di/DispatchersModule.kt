package com.oheyadam.core.common.network.di

import com.oheyadam.core.common.network.di.qualifier.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

  @Provides
  @IoDispatcher
  fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}
