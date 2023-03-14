package com.zeroninedev.common.domain

import androidx.paging.PagingData
import com.zeroninedev.common.domain.models.Manga
import com.zeroninedev.common.domain.models.UpdatedManga
import kotlinx.coroutines.flow.Flow

/**
 * Interface of repository for request in manga service
 *
 */
interface MangaRepository {

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
    fun popularManga(): Flow<PagingData<UpdatedManga>>

    /**
     * Get manga with detail information
     *
     * @param mangaId manga id
     */
    suspend fun mangaDetail(mangaId: String): Manga

    /**
     * Get manga chapter
     *
     * @param mangaId manga id
     * @param chapterId chapter id
     */
    suspend fun mangaChapter(mangaId: String, chapterId: String): List<String>

    /**
     * Save information about status manga
     *
     * @param manga manga
     */
    suspend fun updateMangaStatus(manga: Manga)

    /**
     * Save information about read status chapter
     *
     * @param mangaId manga id
     * @param chapterId chapter id
     */
    suspend fun saveWasReadPage(mangaId: String, chapterId: String)
}