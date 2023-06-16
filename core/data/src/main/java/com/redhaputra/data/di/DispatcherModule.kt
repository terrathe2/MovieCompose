package com.redhaputra.data.di

import com.redhaputra.data.repositories.CommonDispatcherRepository
import com.redhaputra.data.repositories.impl.CommonDispatcherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Class that provide dispatcher
 */
@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {
    /**
     * function that provide coroutine common dispatcher (ex: io, main)
     */
    @Singleton
    @Provides
    fun provideDispatcher(): CommonDispatcherRepository = CommonDispatcherRepositoryImpl()
}