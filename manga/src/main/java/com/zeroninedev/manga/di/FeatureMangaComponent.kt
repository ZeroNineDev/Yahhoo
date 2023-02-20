package com.zeroninedev.manga.di

import com.zeroninedev.common.di.CoreNetworkModule
import com.zeroninedev.common.di.CoroutineModule
import com.zeroninedev.common.di.FeatureMangaModule
import com.zeroninedev.common.domain.NetworkRepository
import com.zeroninedev.manga.presentation.detail.viewmodelfactory.DetailMangaFactory
import com.zeroninedev.manga.presentation.lastupdated.viewmodelfactory.LastUpdatedMangaFactory
import com.zeroninedev.manga.presentation.mangachapter.viewmodelfactory.MangaChapterFactory
import com.zeroninedev.manga.presentation.popular.viewmodelfactory.PopularMangaFactory
import com.zeroninedev.manga.presentation.search.viewmodelfactory.SearchMangaFactory
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        FeatureMangaModule::class,
        CoreNetworkModule::class,
        CoroutineModule::class,
        MangaModule::class
    ]
)
@Singleton
internal interface FeatureMangaComponent {

    @Component.Builder
    interface Builder {

        fun build(): FeatureMangaComponent
    }

    fun provideNetworkRepository(): NetworkRepository

    fun provideLastUpdatedMangaFactory(): LastUpdatedMangaFactory

    fun provideSearchMangaFactory(): SearchMangaFactory

    fun providePopularMangaFactory(): PopularMangaFactory

    fun provideDetailMangaFactory(): DetailMangaFactory

    fun provideMangaChapterFactory(): MangaChapterFactory
}