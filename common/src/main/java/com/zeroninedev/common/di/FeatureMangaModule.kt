package com.zeroninedev.common.di

import com.zeroninedev.common.data.repository.MangaRepositoryImpl
import com.zeroninedev.common.data.repository.SettingRepositoryImpl
import com.zeroninedev.common.domain.MangaRepository
import com.zeroninedev.common.domain.SettingRepository
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

    @Binds
    fun bindSettingRepository(repository: SettingRepositoryImpl): SettingRepository
}