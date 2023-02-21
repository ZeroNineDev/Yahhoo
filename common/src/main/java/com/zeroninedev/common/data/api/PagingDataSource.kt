package com.zeroninedev.common.data.api

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zeroninedev.common.domain.models.UpdatedManga
import com.zeroninedev.common.domain.models.toDomain
import javax.inject.Inject

class PagingDataSource @Inject constructor(private val api: MangaApi) : PagingSource<Int, UpdatedManga>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UpdatedManga> =
        try {
            val page = params.key ?: 1
            val response = api.popularMangas(page)
            val nextKey = if (response.pagesMax == page) null else page + 1
            val prevKey = if (page == 1) null else page - 1
            Log.d("PagingDataSource", "page: $page, nextKey: $nextKey, prevKey: $prevKey")
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
}