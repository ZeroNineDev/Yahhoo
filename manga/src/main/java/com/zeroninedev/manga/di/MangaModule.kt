package com.zeroninedev.manga.di

import com.zeroninedev.manga.data.StateRepositoryImpl
import com.zeroninedev.manga.domain.StateRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Manga component provider
 *
 */
@Module
@InstallIn(SingletonComponent::class)
internal interface MangaModule {

    @Binds
    @Singleton
    fun bindStateRepository(impl: StateRepositoryImpl): StateRepository
}