package com.zeroninedev.common.di

import com.zeroninedev.common.data.repository.NetworkRepositoryImpl
import com.zeroninedev.common.domain.NetworkRepository
import dagger.Binds
import dagger.Module

/**
 * Module which provide manga repository
 *
 */
@Module
interface FeatureMangaModule {

    @Binds
    fun bindNetworkRepository(repository: NetworkRepositoryImpl): NetworkRepository
}