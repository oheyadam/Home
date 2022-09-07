package com.oheyadam.feature.detail

import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth.assertThat
import com.oheyadam.core.common.R
import com.oheyadam.core.common.network.Result
import com.oheyadam.core.testing.model.BREED_1
import com.oheyadam.core.testing.util.CoroutineDispatcherRule
import com.oheyadam.library.analytics.tracker.Trackers
import com.oheyadam.library.data.usecase.GetDog
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever
import java.net.UnknownHostException

class BreedViewModelTest {

  @get:Rule
  val dispatcherRule = CoroutineDispatcherRule()

  private val getDog: GetDog = mock()
  private val savedStateHandle: SavedStateHandle = mock()
  private val trackers: Trackers = mock()

  private lateinit var viewModel: BreedViewModel

  @Before
  fun setup() {
    whenever(savedStateHandle.get<String>("breedName")).thenReturn(BREED_1.name)
    whenever(savedStateHandle.get<Int>("breedId")).thenReturn(BREED_1.id)

    viewModel = BreedViewModel(savedStateHandle, getDog, trackers)
  }

  @Test
  fun `marking an error as shown produces a new state with its resId set to null`() = runTest {
    viewModel.errorMessageShown()

    val actual = viewModel.state

    assertThat(actual.errorResId).isNull()
  }

  @Test(expected = IllegalArgumentException::class)
  fun `getting dog details isn't triggered if the breed name is null`() = runTest {
    whenever(savedStateHandle.get<String?>("breedName")).thenReturn(null)

    viewModel.getDogDetails()

    verifyNoInteractions(getDog)
  }

  @Test(expected = IllegalArgumentException::class)
  fun `getting dog details isn't triggered if the breed id is null`() = runTest {
    whenever(savedStateHandle.get<Int?>("breedId")).thenReturn(null)

    viewModel.getDogDetails()

    verifyNoInteractions(getDog)
  }

  @Test
  fun `getting dog details produces the correct state when the request is successful`() = runTest {
    whenever(getDog(BREED_1.id, BREED_1.name)).thenReturn(Result.Success(BREED_1))

    viewModel.getDogDetails()

    val actual = viewModel.state
    assertThat(actual.isLoading).isFalse()
    assertThat(actual.id).isEqualTo(BREED_1.id)
    assertThat(actual.name).isEqualTo(BREED_1.name)
    assertThat(actual.temperament).isEqualTo(BREED_1.temperament)
    assertThat(actual.thumbnailUrl).isEqualTo(BREED_1.thumbnailUrl)
  }

  @Test
  fun `searching produces the correct state when the request errors out`() = runTest {
    whenever(getDog(BREED_1.id, BREED_1.name)).thenReturn(Result.Error(404))

    viewModel.getDogDetails()

    val actual = viewModel.state
    assertThat(actual.isLoading).isFalse()
    assertThat(actual.errorResId).isEqualTo(R.string.error_generic)
  }

  @Test
  fun `searching emits no network state when the request throws UnknownHostException`() = runTest {
    val exception = UnknownHostException()
    whenever(getDog(BREED_1.id, BREED_1.name)).thenReturn(Result.Exception(exception))

    viewModel.getDogDetails()

    val actual = viewModel.state
    assertThat(actual.isLoading).isFalse()
    assertThat(actual.errorResId).isEqualTo(R.string.error_no_internet_connection)
  }

  @Test
  fun `searching emits generic error state when the request throws an exception`() = runTest {
    val exception = IllegalArgumentException()
    whenever(getDog(BREED_1.id, BREED_1.name)).thenReturn(Result.Exception(exception))

    viewModel.getDogDetails()

    val actual = viewModel.state
    assertThat(actual.isLoading).isFalse()
    assertThat(actual.errorResId).isEqualTo(R.string.error_generic)
  }

  @Test
  fun `searching tracks thrown exceptions when the request throws`() = runTest {
    val exception = IllegalArgumentException()
    whenever(getDog(BREED_1.id, BREED_1.name)).thenReturn(Result.Exception(exception))

    viewModel.getDogDetails()

    verify(trackers).error(exception)
  }
}
