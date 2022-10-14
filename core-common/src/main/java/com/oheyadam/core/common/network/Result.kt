package com.oheyadam.core.common.network

sealed class Result<out T> {
  data class Success<out T>(val data: T) : Result<T>()
  data class Error(val code: Int) : Result<Nothing>()
  data class Exception(val throwable: Throwable) : Result<Nothing>()
}

/**
 * Represents a network result that successfully received a response containing body data.
 * */
suspend fun <T> Result<T>.onSuccess(
  block: suspend (T) -> Unit,
): Result<T> = apply {
  if (this is Result.Success) {
    block(data)
  }
}

/**
 * Represents a network result that successfully received a response containing an error message.
 * */
suspend fun <T> Result<T>.onError(
  block: suspend (code: Int) -> Unit,
): Result<T> = apply {
  if (this is Result.Error) {
    block(code)
  }
}

/**
 * Represents a network result that faced an unexpected exception before getting a response
 * from the network such as IOException.
 * */
suspend fun <T> Result<T>.onException(
  block: suspend (throwable: Throwable) -> Unit,
): Result<T> = apply {
  if (this is Result.Exception) {
    block(throwable)
  }
}
