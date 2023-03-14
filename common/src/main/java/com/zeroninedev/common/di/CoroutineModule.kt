package com.zeroninedev.common.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Base module for all flow
 *
 */
@Module
class CoroutineModule {

    @Provides
    @IoDispatcher
    fun coroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO
}