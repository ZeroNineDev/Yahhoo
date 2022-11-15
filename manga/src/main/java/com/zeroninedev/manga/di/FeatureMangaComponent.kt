package com.zeroninedev.manga.di

import com.zeroninedev.common.di.CoroutineModule
import com.zeroninedev.manga.domain.NetworkRepository
import com.zeroninedev.manga.presentation.viewmodelfactory.DetailMangaFactory
import com.zeroninedev.manga.presentation.viewmodelfactory.LastUpdatedMangaFactory
import com.zeroninedev.manga.presentation.viewmodelfactory.PopularMangaFactory
import dagger.Component

@Component(
    modules = [
        FeatureNetworkModule::class,
        FeatureMangaModule::class,
        CoroutineModule::class,
    ]
)
internal interface FeatureMangaComponent {

    @Component.Builder
    interface Builder {

        fun build(): FeatureMangaComponent
    }

    fun provideNetworkRepository(): NetworkRepository

    fun provideLastUpdatedMangaFactory(): LastUpdatedMangaFactory

    fun providePopularMangaFactory(): PopularMangaFactory

    fun provideDetailMangaFactory(): DetailMangaFactory
}