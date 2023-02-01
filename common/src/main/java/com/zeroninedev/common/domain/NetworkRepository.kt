package com.zeroninedev.common.domain

import com.zeroninedev.common.domain.models.Manga
import com.zeroninedev.common.domain.models.UpdatedManga

interface NetworkRepository {

    /**
     * Get last updated manga
     */
    suspend fun lastUpdatedMangas(): List<UpdatedManga>

    /**
     * Get manga by search parameter
     *
     * @param mangaName query manga
     */
    suspend fun searchMangaByName(mangaName: String): List<UpdatedManga>

    /**
     * Get popular manga
     */
    suspend fun popularManga(): List<UpdatedManga>

    /**
     * Get manga with detail information
     */
    suspend fun mangaDetail(mangaId: String): Manga

    /**
     * Get manga chapter
     */
    suspend fun mangaChapter(mangaId: String, chapterId: String): List<String>
}