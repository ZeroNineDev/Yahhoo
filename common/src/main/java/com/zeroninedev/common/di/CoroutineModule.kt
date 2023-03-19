package com.zeroninedev.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Base module for all flow
 *
 */
@Module
@InstallIn(SingletonComponent::class)
class CoroutineModule {

    @Provides
    @IoDispatcher
    fun coroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO
}