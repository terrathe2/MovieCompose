package com.redhaputra.data.repositories

import kotlin.coroutines.CoroutineContext

/**
 * Repository module for common dispatcher used (important for unit testing later).
 */
interface CommonDispatcherRepository {
    val io: CoroutineContext
    val main: CoroutineContext
}