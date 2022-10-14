package com.oheyadam.library.data.usecase

import com.oheyadam.core.common.network.Result
import com.oheyadam.core.common.network.Result.Error
import com.oheyadam.core.common.network.Result.Exception
import com.oheyadam.core.common.network.Result.Success
import com.oheyadam.core.model.Comment
import javax.inject.Inject

data class Node(
  val parent: Comment,
  val children: MutableList<Node>,
)

class GetStoryComments @Inject constructor(private val getComment: GetComment) {

  suspend operator fun invoke(parentCommentIds: List<Long>): Result<List<Node>> {
    val nodes = mutableListOf<Node>()
    val commentsTreeResult = parentCommentIds
      .map { parentCommentId ->
        fetch(parentCommentId) { parentComment ->
          val parentNode = Node(parentComment, mutableListOf())
          nodes.add(parentNode)
          dfs(parentNode, nodes)
        }
      }
      .last()
    return when (commentsTreeResult) {
      is Success -> Success(nodes)
      is Error -> Error(commentsTreeResult.code)
      is Exception -> Exception(commentsTreeResult.throwable)
    }
  }

  private suspend fun dfs(
    node: Node,
    result: MutableList<Node>,
  ) {
    val childCommentsIds = node.parent.kids
    if (childCommentsIds.isNullOrEmpty()) return
    childCommentsIds.forEach { childCommentId ->
      val response = fetch(childCommentId) { childComment ->
        val childNode = Node(childComment, mutableListOf())
        node.children.add(childNode)
        dfs(childNode, result)
      }
      if (response is Error || response is Exception) return@forEach
    }
  }

  private suspend fun fetch(
    id: Long,
    operation: suspend (Comment) -> Unit,
  ): Result<Comment> {
    return when (val response = getComment(id)) {
      is Success -> {
        val data = response.data
        operation(data)
        Success(data)
      }

      is Error -> Error(response.code)
      is Exception -> Exception(response.throwable)
    }
  }
}
