package com.zeroninedev.common.data.api

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zeroninedev.common.data.dbmodels.ChaptersModel
import com.zeroninedev.common.data.dbmodels.MangaModel
import javax.inject.Singleton

@Database(entities = [ChaptersModel::class, MangaModel::class], version = 2)
@Singleton
abstract class MangaDatabase : RoomDatabase() {

    abstract fun mangaDao(): MangaDao
}