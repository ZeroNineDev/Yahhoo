package com.zeroninedev.common.di

import android.content.Context
import androidx.room.Room
import com.zeroninedev.common.constants.Constants.MANGA_DATABASE_NAME
import com.zeroninedev.common.data.api.MangaDatabase
import com.zeroninedev.common.data.sharedprefs.SharedPreferencesProvider
import com.zeroninedev.common.data.sharedprefs.SharedPreferencesProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {

    @Singleton
    @Provides
    fun provideMangaDatabase(@ApplicationContext context: Context): MangaDatabase =
        Room.databaseBuilder(context, MangaDatabase::class.java, MANGA_DATABASE_NAME)
            .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideSharedPrefs(@ApplicationContext context: Context): SharedPreferencesProvider {
        return SharedPreferencesProviderImpl(context = context)
    }
}