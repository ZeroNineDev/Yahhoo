package com.zeroninedev.manga.di

import com.zeroninedev.manga.data.StateRepositoryImpl
import com.zeroninedev.manga.domain.StateRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal interface MangaModule {

    @Binds
    @Singleton
    fun bindStateRepository(impl: StateRepositoryImpl): StateRepository
}