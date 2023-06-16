package com.redhaputra.data.repositories.impl

import com.redhaputra.data.repositories.CommonDispatcherRepository
import kotlinx.coroutines.Dispatchers

/**
 * Repository module for handling common dispatcher used operations.
 */
class CommonDispatcherRepositoryImpl : CommonDispatcherRepository {
    override val io by lazy { Dispatchers.IO }
    override val main by lazy { Dispatchers.Default }
}