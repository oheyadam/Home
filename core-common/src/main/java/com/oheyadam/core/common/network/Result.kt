package com.oheyadam.core.common.network

sealed class Result<T> {
  class Success<T>(val data: T) : Result<T>()
  class Error<T>(val code: Int) : Result<T>()
  class Exception<T>(val throwable: Throwable) : Result<T>()
}

/**
 * Represents a network result that successfully received a response containing body data.
 * */
suspend fun <T> Result<T>.onSuccess(
  block: suspend (T) -> Unit
): Result<T> = apply {
  if (this is Result.Success) {
    block(data)
  }
}

/**
 * Represents a network result that successfully received a response containing an error message.
 * */
suspend fun <T> Result<T>.onError(
  block: suspend (code: Int) -> Unit
): Result<T> = apply {
  if (this is Result.Error<T>) {
    block(code)
  }
}

/**
 * Represents a network result that faced an unexpected exception before getting a response
 * from the network such as IOException.
 * */
suspend fun <T> Result<T>.onException(
  block: suspend (throwable: Throwable) -> Unit
): Result<T> = apply {
  if (this is Result.Exception) {
    block(throwable)
  }
}
