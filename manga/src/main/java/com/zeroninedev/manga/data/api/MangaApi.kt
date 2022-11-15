package com.zeroninedev.manga.data.api

import com.zeroninedev.manga.data.models.MangaDto
import com.zeroninedev.manga.data.models.SearchResponseDto
import com.zeroninedev.manga.data.models.UpdatedMangaDto
import retrofit2.http.GET
import retrofit2.http.Path

internal interface MangaApi {

    /**
     * Get last updated manga
     */
    @GET(LAST_UPDATED_MANGA)
    suspend fun lastUpdatedMangas() : List<UpdatedMangaDto>

    /**
     * Get pages with popular mangas
     */
    @GET(POPULAR_MANGA)
    suspend fun popularMangas(@Path("page") page: Int) : SearchResponseDto

    /**
     * Get detail about manga with chapter
     */
    @GET(MANGA_DETAIL)
    suspend fun mangaDetail(@Path("mangaId") mangaId: String) : MangaDto

    private companion object {

        const val LAST_UPDATED_MANGA = "/"
        const val POPULAR_MANGA = "/mangas/{page}"
        const val MANGA_DETAIL = "/manga/{mangaId}"
    }
}