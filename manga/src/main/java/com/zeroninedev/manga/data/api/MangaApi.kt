package com.zeroninedev.manga.data.api

import com.zeroninedev.manga.data.models.UpdatedMangaDto
import retrofit2.http.GET

internal interface MangaApi {

    /**
     * Get last updated manga
     */
    @GET(LAST_UPDATED_MANGA)
    suspend fun lastUpdatedMangas() : List<UpdatedMangaDto>

    companion object {

        const val LAST_UPDATED_MANGA = "/"
    }
}