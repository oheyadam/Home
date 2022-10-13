package com.oheyadam.library.data.usecase.hackernews

import com.google.common.truth.Truth.assertThat
import com.oheyadam.core.common.network.Result
import com.oheyadam.core.testing.util.CoroutineDispatcherRule
import com.oheyadam.library.data.source.hackernews.fake.COMMENT_1
import com.oheyadam.library.data.source.hackernews.fake.COMMENT_2
import com.oheyadam.library.data.source.hackernews.fake.COMMENT_3
import com.oheyadam.library.data.source.hackernews.fake.COMMENT_4
import com.oheyadam.library.data.source.hackernews.fake.COMMENT_5
import com.oheyadam.library.data.source.hackernews.fake.COMMENT_6
import com.oheyadam.library.data.source.hackernews.fake.COMMENT_7
import com.oheyadam.library.data.source.hackernews.fake.FakeHackerNewsRemoteSource
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetStoryCommentsTest {

  @get:Rule val dispatcherRule = CoroutineDispatcherRule()

  private val getComment = GetComment(FakeHackerNewsRemoteSource())

  private lateinit var getStoryComments: GetStoryComments

  @Before fun setup() {
    getStoryComments = GetStoryComments(getComment = getComment)
  }

  @Test fun buildCommentsTree() = runTest {
    val actual = getStoryComments(listOf(1, 2))
    val expected = setOf(
      Node(
        COMMENT_1, mutableListOf(
        Node(COMMENT_3, mutableListOf()),
        Node(
          COMMENT_4, mutableListOf(
          Node(
            COMMENT_5,
            mutableListOf(Node(COMMENT_6, mutableListOf()), Node(COMMENT_7, mutableListOf()))
          )
        )
        ),
      )
      ), Node(COMMENT_2, mutableListOf())
    )

    assertThat((actual as Result.Success).data).containsExactlyElementsIn(expected)
  }
}
