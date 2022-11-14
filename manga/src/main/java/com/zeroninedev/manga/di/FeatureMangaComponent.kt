package com.zeroninedev.manga.di

import com.zeroninedev.common.di.CoroutineModule
import com.zeroninedev.manga.domain.NetworkRepository
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
}