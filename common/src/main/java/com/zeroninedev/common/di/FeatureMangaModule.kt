package com.zeroninedev.common.di

import com.zeroninedev.common.data.repository.MangaRepositoryImpl
import com.zeroninedev.common.domain.MangaRepository
import dagger.Binds
import dagger.Module

/**
 * Module which provide manga repository
 *
 */
@Module
interface FeatureMangaModule {

    @Binds
    fun bindNetworkRepository(repository: MangaRepositoryImpl): MangaRepository
}