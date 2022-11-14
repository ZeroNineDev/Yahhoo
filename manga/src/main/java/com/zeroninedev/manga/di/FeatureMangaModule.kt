package com.zeroninedev.manga.di

import com.zeroninedev.manga.data.repository.NetworkRepositoryImpl
import com.zeroninedev.manga.domain.NetworkRepository
import dagger.Binds
import dagger.Module

@Module
internal interface FeatureMangaModule {

    @Binds
    fun bindNetworkRepository(repository: NetworkRepositoryImpl): NetworkRepository
}