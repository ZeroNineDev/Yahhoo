package com.zeroninedev.manga.di

import android.content.Context
import com.zeroninedev.common.di.CoreNetworkModule
import com.zeroninedev.common.di.CoroutineModule
import com.zeroninedev.common.di.FeatureMangaModule
import com.zeroninedev.common.di.StorageModule
import com.zeroninedev.manga.presentation.category.viewmodelfactory.CategoryMangaFactory
import com.zeroninedev.manga.presentation.detail.viewmodelfactory.DetailMangaFactory
import com.zeroninedev.manga.presentation.lastupdated.viewmodelfactory.LastUpdatedMangaFactory
import com.zeroninedev.manga.presentation.mangachapter.viewmodelfactory.MangaChapterFactory
import com.zeroninedev.manga.presentation.popular.viewmodelfactory.PopularMangaFactory
import com.zeroninedev.manga.presentation.saved.viewmodelfactory.SavedMangaFactory
import com.zeroninedev.manga.presentation.search.viewmodelfactory.SearchMangaFactory
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Base manga component
 *
 */
@Component(
    modules = [
        FeatureMangaModule::class,
        CoreNetworkModule::class,
        CoroutineModule::class,
        MangaModule::class,
        StorageModule::class
    ]
)
@Singleton
internal interface FeatureMangaComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder
        fun build(): FeatureMangaComponent
    }

    fun provideLastUpdatedMangaFactory(): LastUpdatedMangaFactory

    fun provideSearchMangaFactory(): SearchMangaFactory

    fun providePopularMangaFactory(): PopularMangaFactory

    fun provideDetailMangaFactory(): DetailMangaFactory

    fun provideMangaChapterFactory(): MangaChapterFactory

    fun provideCategoryMangaFactory(): CategoryMangaFactory

    fun provideSavedMangaFactory(): SavedMangaFactory
}