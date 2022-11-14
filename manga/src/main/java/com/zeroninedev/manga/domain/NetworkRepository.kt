package com.zeroninedev.manga.domain

import com.zeroninedev.manga.domain.models.UpdatedManga

internal interface NetworkRepository {

    /**
     * Get last updated manga
     */
    suspend fun lastUpdatedMangas() : List<UpdatedManga>
}