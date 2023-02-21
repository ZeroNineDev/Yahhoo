package com.zeroninedev.common.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.zeroninedev.common.di.IoDispatcher
import com.zeroninedev.common.data.api.MangaApi
import com.zeroninedev.common.data.api.PagingDataSource
import com.zeroninedev.common.domain.NetworkRepository
import com.zeroninedev.common.domain.models.Manga
import com.zeroninedev.common.domain.models.UpdatedManga
import com.zeroninedev.common.domain.models.toDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val api: MangaApi,
    @IoDispatcher
    private val dispatcher: CoroutineDispatcher
) : NetworkRepository {

    override suspend fun lastUpdatedMangas(): List<UpdatedManga> = withContext(dispatcher) {
        api.lastUpdatedMangas().map { it.toDomain() }
    }

    override suspend fun searchMangaByName(mangaName: String): List<UpdatedManga> = withContext(dispatcher) {
        api.searchMangaByName(mangaName).map { it.toDomain() }
    }

    override fun popularManga(): Flow<PagingData<UpdatedManga>> = paging()

    override suspend fun mangaDetail(mangaId: String): Manga = withContext(dispatcher) {
        api.mangaDetail(mangaId).toDomain()
    }

    override suspend fun mangaChapter(mangaId: String, chapterId: String): List<String> = withContext(dispatcher) {
        api.mangaPages(mangaId, chapterId)
    }

    private fun paging() = Pager(
        config = PagingConfig(100),
        pagingSourceFactory = { PagingDataSource(api = api) }
    ).flow
}