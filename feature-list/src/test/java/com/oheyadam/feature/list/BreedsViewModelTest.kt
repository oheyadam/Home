package com.oheyadam.feature.list

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.oheyadam.core.common.R
import com.oheyadam.core.common.network.Result
import com.oheyadam.core.testing.model.BREEDS
import com.oheyadam.core.testing.model.BREED_2
import com.oheyadam.core.testing.util.CoroutineDispatcherRule
import com.oheyadam.feature.list.model.BREED_ITEMS
import com.oheyadam.library.analytics.tracker.Trackers
import com.oheyadam.library.data.usecase.SearchBreed
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever
import java.net.UnknownHostException

class BreedsViewModelTest {

  @get:Rule
  val dispatcherRule = CoroutineDispatcherRule()

  private val searchBreed: SearchBreed = mock()
  private val trackers: Trackers = mock()

  private lateinit var viewModel: BreedsViewModel

  @Before
  fun setup() {
    viewModel = BreedsViewModel(searchBreed, trackers)
  }

  @Test
  fun `selecting a breed produces a new state with its id and name`() = runTest {
    viewModel.selectedBreed(BREED_2.id, BREED_2.name)

    viewModel.state.test {
      val actual = awaitItem()
      assertThat(actual.selectedBreedId).isEqualTo(BREED_2.id)
      assertThat(actual.selectedBreedName).isEqualTo(BREED_2.name)
    }
  }

  @Test
  fun `marking a breed as selected produces a new state with its id set to null`() = runTest {
    viewModel.breedSelected()

    viewModel.state.test {
      val actual = awaitItem()
      assertThat(actual.selectedBreedId).isNull()
    }
  }

  @Test
  fun `marking an error as shown produces a new state with its resId set to null`() = runTest {
    viewModel.errorMessageShown()

    viewModel.state.test {
      val actual = awaitItem()
      assertThat(actual.errorResId).isNull()
    }
  }

  @Test
  fun `calling clear produces a fresh state`() = runTest {
    viewModel.clear()

    viewModel.state.test {
      val actual = awaitItem()
      assertThat(actual.isLoading).isFalse()
      assertThat(actual.query).isEmpty()
      assertThat(actual.result).isEmpty()
    }
  }

  @Test
  fun `searching isn't triggered if the query is null`() = runTest {
    viewModel.search(null)
    verifyNoInteractions(searchBreed)
  }

  @Test
  fun `searching isn't triggered if the query is blank`() = runTest {
    viewModel.search(" ")
    verifyNoInteractions(searchBreed)
  }

  @Test
  fun `searching isn't triggered if the query is empty`() = runTest {
    viewModel.search("")
    verifyNoInteractions(searchBreed)
  }

  @Test
  fun `searching produces the correct state when the request is successful`() = runTest {
    val query = "query"
    whenever(searchBreed(query)).thenReturn(Result.Success(BREEDS))

    viewModel.search(query)

    viewModel.state.test {
      val actual = awaitItem()
      assertThat(actual.isLoading).isFalse()
      assertThat(actual.query).isEqualTo(query)
      assertThat(actual.errorResId).isNull()
      assertThat(actual.result).containsExactlyElementsIn(BREED_ITEMS)
    }
  }

  @Test
  fun `searching produces the correct state when the request errors out`() = runTest {
    val query = "query"
    whenever(searchBreed(query)).thenReturn(Result.Error(404))

    viewModel.search(query)

    viewModel.state.test {
      val actual = awaitItem()
      assertThat(actual.isLoading).isFalse()
      assertThat(actual.query).isEqualTo(query)
      assertThat(actual.errorResId).isEqualTo(R.string.error_generic)
    }
  }

  @Test
  fun `searching emits no network state when the request throws UnknownHostException`() = runTest {
    val query = "query"
    val exception = UnknownHostException()
    whenever(searchBreed(query)).thenReturn(Result.Exception(exception))

    viewModel.search(query)

    viewModel.state.test {
      val actual = awaitItem()
      assertThat(actual.isLoading).isFalse()
      assertThat(actual.query).isEqualTo(query)
      assertThat(actual.errorResId).isEqualTo(R.string.error_no_internet_connection)
    }
  }

  @Test
  fun `searching emits generic error state when the request throws an exception`() = runTest {
    val query = "query"
    val exception = IllegalArgumentException()
    whenever(searchBreed(query)).thenReturn(Result.Exception(exception))

    viewModel.search(query)

    viewModel.state.test {
      val actual = awaitItem()
      assertThat(actual.isLoading).isFalse()
      assertThat(actual.query).isEqualTo(query)
      assertThat(actual.errorResId).isEqualTo(R.string.error_generic)
    }
  }

  @Test
  fun `searching tracks thrown exceptions when the request throws`() = runTest {
    val query = "query"
    val exception = IllegalArgumentException()
    whenever(searchBreed(query)).thenReturn(Result.Exception(exception))

    viewModel.search(query)

    verify(trackers).error(exception)
  }
}
