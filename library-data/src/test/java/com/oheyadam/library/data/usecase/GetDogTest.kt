package com.oheyadam.library.data.usecase

import com.google.common.truth.Truth.assertThat
import com.oheyadam.core.common.network.Result
import com.oheyadam.core.testing.model.BREED_1
import com.oheyadam.core.testing.util.CoroutineDispatcherRule
import com.oheyadam.library.data.source.BreedRemoteSource
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.net.UnknownHostException

class GetDogTest {

  @get:Rule
  val dispatcherRule = CoroutineDispatcherRule()

  private val remoteSource: BreedRemoteSource = mock()

  private lateinit var getDog: GetDog

  @Before
  fun setup() {
    getDog = GetDog(remoteSource)
  }

  @Test
  fun `invoking maps to correct result subtype when it's successful`() = runTest {
    whenever(remoteSource.getBreed(BREED_1.id, BREED_1.name))
      .thenReturn(Result.Success(BREED_1))

    val actual = getDog(BREED_1.id, BREED_1.name)

    assertThat(actual).isEqualTo(Result.Success(BREED_1))
  }

  @Test
  fun `invoking maps to correct result subtype when it's an error`() = runTest {
    whenever(remoteSource.getBreed(BREED_1.id, BREED_1.name))
      .thenReturn(Result.Error(404))

    val actual = getDog(BREED_1.id, BREED_1.name)

    assertThat(actual).isEqualTo(Result.Error(404))
  }

  @Test
  fun `invoking maps to correct result subtype when it throws an exception`() = runTest {
    val exception = UnknownHostException()
    whenever(remoteSource.getBreed(BREED_1.id, BREED_1.name))
      .thenReturn(Result.Exception(exception))

    val actual = getDog(BREED_1.id, BREED_1.name)

    assertThat(actual).isEqualTo(Result.Exception(exception))
  }
}
