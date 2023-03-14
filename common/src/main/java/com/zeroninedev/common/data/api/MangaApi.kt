package com.zeroninedev.common.data.api

import com.zeroninedev.common.data.models.MangaDto
import com.zeroninedev.common.data.models.SearchResponseDto
import com.zeroninedev.common.data.models.UpdatedMangaDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interface for request in our manga service.
 *
 */
interface MangaApi {

    /**
     * Get last updated manga
     */
    @GET(LAST_UPDATED_MANGA)
    suspend fun lastUpdatedMangas(): List<UpdatedMangaDto>

    /**
     * Get manga by search parameter
     *
     * @param mangaName query manga name
     */
    @GET(SEARCH_MANGA)
    suspend fun searchMangaByName(@Query("name") mangaName: String): List<UpdatedMangaDto>

    /**
     * Get pages with popular mangas
     *
     * @param page page to load
     */
    @GET(POPULAR_MANGA)
    suspend fun popularMangas(@Path("page") page: Int): SearchResponseDto

    /**
     * Get pages with categories mangas
     *
     * @param categoryId id of category
     * @param page page to load
     */
    @GET(CATEGORY_MANGA)
    suspend fun categoryMangas(@Path("categoryId") categoryId: String, @Path("page") page: Int): SearchResponseDto

    /**
     * Get detail about manga with chapter
     *
     * @param mangaId manga id
     */
    @GET(MANGA_DETAIL)
    suspend fun mangaDetail(@Path("mangaId") mangaId: String): MangaDto

    /**
     * Get pages of chapter manga
     *
     *  @param mangaId manga id
     *  @param chapterId chapter id
     */
    @GET(MANGA_PAGES)
    suspend fun mangaPages(@Path("mangaId") mangaId: String, @Path("chapterId") chapterId: String): List<String>

    private companion object {

        const val LAST_UPDATED_MANGA = "/"
        const val SEARCH_MANGA = "/search"
        const val POPULAR_MANGA = "/mangas/{page}"
        const val MANGA_DETAIL = "/manga/{mangaId}"
        const val CATEGORY_MANGA = "/mangas/{categoryId}/{page}"
        const val MANGA_PAGES = "/manga/{mangaId}/{chapterId}"
    }
}