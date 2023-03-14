package com.zeroninedev.common.data.api

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zeroninedev.common.constants.Constants
import com.zeroninedev.common.data.models.toDomain
import com.zeroninedev.common.domain.models.UpdatedManga

/**
 * Realization of pagination to load categories mangas
 *
 * @property api interface for manga
 */
class CategoryDataSource(
    private val category: String,
    private val api: MangaApi
) : PagingSource<Int, UpdatedManga>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UpdatedManga> =
        try {
            val page = params.key ?: START_POSITION
            val response = api.categoryMangas(category, page)
            val nextKey = if (response.pagesMax == page) null else page + START_POSITION
            val prevKey = if (page == START_POSITION) null else page - START_POSITION
            Log.d(Constants.PAGING_LOG, "page: $page, nextKey: $nextKey, prevKey: $prevKey")
            LoadResult.Page(
                data = response.mangas.map { it.toDomain() },
                nextKey = nextKey,
                prevKey = prevKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    override fun getRefreshKey(state: PagingState<Int, UpdatedManga>): Int? =
        state.anchorPosition

    private companion object {

        const val START_POSITION = 1
    }
}