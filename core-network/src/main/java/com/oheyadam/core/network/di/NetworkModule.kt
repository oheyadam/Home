package com.oheyadam.core.network.di

import android.content.Context
import com.oheyadam.core.network.service.HackerNewsService
import com.squareup.moshi.Moshi
import dagger.Provides
import dagger.Reusable
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.multibindings.IntoSet
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Singleton
object NetworkModule {

  @Provides
  @IntoSet
  fun provideLoggingInterceptor(): Interceptor {
    return HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BODY
    }
  }

  @Provides
  @Singleton
  fun provideCache(@ApplicationContext context: Context): Cache {
    val size: Long = 10 * 1024 * 1024
    return Cache(context.cacheDir, size)
  }

  @Provides
  @Singleton
  fun provideOkHttp(
    interceptors: Set<@JvmSuppressWildcards Interceptor>,
    cache: Cache
  ): OkHttpClient {
    return OkHttpClient.Builder()
      .apply { interceptors.forEach { interceptor -> addInterceptor(interceptor) } }
      .cache(cache)
      .build()
  }

  @Provides
  @Singleton
  fun provideMoshi(): Moshi {
    return Moshi.Builder().build()
  }

  @Provides
  @Reusable
  fun provideRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit {
    return Retrofit.Builder()
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .client(client)
      .baseUrl("https://hacker-news.firebaseio.com/v0/")
      .build()
  }

  @Provides
  @Singleton
  fun provideHackerNewsService(retrofit: Retrofit): HackerNewsService {
    return retrofit.create()
  }
}
