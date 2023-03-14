package com.zeroninedev.common.di

import android.content.Context
import androidx.room.Room
import com.zeroninedev.common.constants.Constants.MANGA_DATABASE_NAME
import com.zeroninedev.common.data.api.MangaDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    @Singleton
    @Provides
    fun provideMangaDatabase(context: Context): MangaDatabase =
        Room.databaseBuilder(context, MangaDatabase::class.java, MANGA_DATABASE_NAME)
            .fallbackToDestructiveMigration().build()
}