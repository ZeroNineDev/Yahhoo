package com.zeroninedev.manga.di

import com.zeroninedev.common.di.CoreNetworkModule
import com.zeroninedev.manga.data.api.MangaApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module(includes = [CoreNetworkModule::class])
internal class FeatureNetworkModule {

    @Provides
    fun provideMangaApiService(retrofit: Retrofit): MangaApi = retrofit.create()
}