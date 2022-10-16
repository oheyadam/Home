package com.oheyadam.core.common.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.shareIn

fun <T> Flow<T>.cachedIn(
  scope: CoroutineScope,
): Flow<T> {
  return shareIn(
    scope = scope,
    started = WhileSubscribedOrRetained,
    replay = 1
  )
}
