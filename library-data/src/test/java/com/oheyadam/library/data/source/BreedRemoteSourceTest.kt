package com.oheyadam.library.data.source

import com.google.common.truth.Truth.assertThat
import com.oheyadam.core.common.network.Result
import com.oheyadam.core.network.service.DogService
import com.oheyadam.core.testing.model.BREEDS
import com.oheyadam.core.testing.model.BREED_1
import com.oheyadam.core.testing.model.BREED_2
import com.oheyadam.core.testing.model.remote.BREEDS_RESPONSES
import com.oheyadam.core.testing.model.remote.IMAGE_RESPONSE
import com.oheyadam.core.testing.util.CoroutineDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.net.UnknownHostException

class BreedRemoteSourceTest {

  @get:Rule
  val dispatcherRule = CoroutineDispatcherRule()

  private val dogService: DogService = mock()

  private lateinit var remoteSource: BreedRemoteSource

  @Before
  fun setup() {
    remoteSource = BreedRemoteSource(dogService)
  }

  @Test
  fun `search through the Service`() = runTest {
    val query = "query"
    whenever(dogService.search(query)).thenReturn(Result.Success(BREEDS_RESPONSES))
    whenever(dogService.getImage(IMAGE_RESPONSE.id)).thenReturn(Result.Success(IMAGE_RESPONSE))

    remoteSource.search(query)

    verify(dogService).search(query)
  }

  @Test
  fun `search associates images through the Service`() = runTest {
    val query = "query"
    whenever(dogService.search(query)).thenReturn(Result.Success(BREEDS_RESPONSES))
    whenever(dogService.getImage(IMAGE_RESPONSE.id)).thenReturn(Result.Success(IMAGE_RESPONSE))

    remoteSource.search(query)

    verify(dogService).getImage(IMAGE_RESPONSE.id)
  }

  @Test
  fun `map search response to domain type when it's successful`() = runTest {
    val query = "query"
    whenever(dogService.search(query)).thenReturn(Result.Success(BREEDS_RESPONSES))
    whenever(dogService.getImage(IMAGE_RESPONSE.id)).thenReturn(Result.Success(IMAGE_RESPONSE))

    val actual = remoteSource.search(query)

    assertThat(actual).isEqualTo(Result.Success(BREEDS))
  }

  @Test
  fun `map search response to domain type when it's an error`() = runTest {
    val query = "query"
    whenever(dogService.search(query)).thenReturn(Result.Error(404))

    val actual = remoteSource.search(query)

    assertThat(actual).isEqualTo(Result.Error(404))
  }


  @Test
  fun `map search response to domain type when it's an exception`() = runTest {
    val query = "query"
    val exception = UnknownHostException()
    whenever(dogService.search(query)).thenReturn(Result.Exception(exception))

    val actual = remoteSource.search(query)

    assertThat(actual).isEqualTo(Result.Exception(exception))
  }

  @Test
  fun `populate thumbnailUrl as null if the service request fails`() = runTest {
    val query = "query"
    whenever(dogService.search(query)).thenReturn(Result.Success(BREEDS_RESPONSES))
    whenever(dogService.getImage(IMAGE_RESPONSE.id)).thenReturn(Result.Error(404))

    val actual = remoteSource.search(query)

    assertThat(actual).isInstanceOf(Result.Success::class.java)
    assertThat((actual as (Result.Success)).data.first().thumbnailUrl).isNull()
  }

  @Test
  fun `querying a dog searches through the service`() = runTest {
    whenever(dogService.search(BREED_2.name)).thenReturn(Result.Success(BREEDS_RESPONSES))
    whenever(dogService.getImage(IMAGE_RESPONSE.id)).thenReturn(Result.Success(IMAGE_RESPONSE))

    remoteSource.getBreed(BREED_2.id, BREED_2.name)

    verify(dogService).search(BREED_2.name)
  }

  @Test
  fun `querying a dog associates its image through the service`() = runTest {
    whenever(dogService.search(BREED_1.name)).thenReturn(Result.Success(BREEDS_RESPONSES))
    whenever(dogService.getImage(IMAGE_RESPONSE.id)).thenReturn(Result.Success(IMAGE_RESPONSE))

    remoteSource.getBreed(BREED_1.id, BREED_1.name)

    verify(dogService).getImage(IMAGE_RESPONSE.id)
  }

  @Test
  fun `map get breed response to domain type when it's successful`() = runTest {
    whenever(dogService.search(BREED_1.name)).thenReturn(Result.Success(BREEDS_RESPONSES))
    whenever(dogService.getImage(IMAGE_RESPONSE.id)).thenReturn(Result.Success(IMAGE_RESPONSE))

    val actual = remoteSource.getBreed(BREED_1.id, BREED_1.name)

    assertThat(actual).isEqualTo(Result.Success(BREED_1))
  }

  @Test
  fun `map get breed response to domain type when it's an error`() = runTest {
    whenever(dogService.search(BREED_1.name)).thenReturn(Result.Error(404))

    val actual = remoteSource.search(BREED_1.name)

    assertThat(actual).isEqualTo(Result.Error(404))
  }


  @Test
  fun `map get breed response to domain type when it's an exception`() = runTest {
    val exception = UnknownHostException()
    whenever(dogService.search(BREED_1.name)).thenReturn(Result.Exception(exception))

    val actual = remoteSource.search(BREED_1.name)

    assertThat(actual).isEqualTo(Result.Exception(exception))
  }
}
