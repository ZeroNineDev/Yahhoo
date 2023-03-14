package com.zeroninedev.common.data.api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zeroninedev.common.data.dbmodels.ChaptersModel
import com.zeroninedev.common.data.dbmodels.MangaModel

@Dao
interface MangaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun putManga(manga: MangaModel)

    @Query("SELECT * FROM manga WHERE id = :id")
    suspend fun getManga(id: String): MangaModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun putChapter(chapter: ChaptersModel)

    @Query("SELECT * FROM chapters WHERE manga_key = :mangaKey")
    suspend fun getChapters(mangaKey: String): List<ChaptersModel>?
}