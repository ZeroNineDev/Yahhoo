package com.zeroninedev.manga.domain

import com.zeroninedev.manga.domain.models.Manga
import com.zeroninedev.manga.domain.models.UpdatedManga

internal interface NetworkRepository {

    /**
     * Get last updated manga
     */
    suspend fun lastUpdatedMangas() : List<UpdatedManga>

    /**
     * Get popular manga
     */
    suspend fun popularManga() : List<UpdatedManga>

    /**
     * Get manga with detail information
     */
    suspend fun mangaDetail(mangaId: String) : Manga
}