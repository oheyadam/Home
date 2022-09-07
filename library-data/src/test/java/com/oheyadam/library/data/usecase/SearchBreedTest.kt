package com.oheyadam.library.data.usecase

import com.google.common.truth.Truth.assertThat
import com.oheyadam.core.common.network.Result
import com.oheyadam.core.testing.model.BREEDS
import com.oheyadam.core.testing.util.CoroutineDispatcherRule
import com.oheyadam.library.data.source.BreedRemoteSource
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.net.UnknownHostException

class SearchBreedTest {

  @get:Rule
  val dispatcherRule = CoroutineDispatcherRule()

  private val remoteSource: BreedRemoteSource = mock()

  private lateinit var searchBreed: SearchBreed

  @Before
  fun setup() {
    searchBreed = SearchBreed(remoteSource)
  }

  @Test
  fun `invoking maps to correct result subtype when it's successful`() = runTest {
    val query = "query"
    whenever(remoteSource.search(query)).thenReturn(Result.Success(BREEDS))

    val actual = searchBreed(query)

    assertThat(actual).isEqualTo(Result.Success(BREEDS))
  }

  @Test
  fun `invoking maps to correct result subtype when it's an error`() = runTest {
    val query = "query"
    whenever(remoteSource.search(query)).thenReturn(Result.Error(404))

    val actual = searchBreed(query)

    assertThat(actual).isEqualTo(Result.Error(404))
  }

  @Test
  fun `invoking maps to correct result subtype when it throws an exception`() = runTest {
    val exception = UnknownHostException()
    val query = "query"
    whenever(remoteSource.search(query)).thenReturn(Result.Exception(exception))

    val actual = searchBreed(query)

    assertThat(actual).isEqualTo(Result.Exception(exception))
  }
}
