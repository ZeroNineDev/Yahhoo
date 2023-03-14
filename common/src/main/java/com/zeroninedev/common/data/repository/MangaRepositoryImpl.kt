package com.zeroninedev.common.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.zeroninedev.common.data.api.CategoryDataSource
import com.zeroninedev.common.di.IoDispatcher
import com.zeroninedev.common.data.api.MangaApi
import com.zeroninedev.common.data.api.MangaDatabase
import com.zeroninedev.common.data.api.PagingDataSource
import com.zeroninedev.common.data.dbmodels.ChaptersModel
import com.zeroninedev.common.data.dbmodels.toDomain
import com.zeroninedev.common.data.models.toDomain
import com.zeroninedev.common.domain.MangaRepository
import com.zeroninedev.common.domain.models.Manga
import com.zeroninedev.common.domain.models.UpdatedManga
import com.zeroninedev.common.domain.models.enrichDbData
import com.zeroninedev.common.domain.models.toDatabaseModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Realization of [MangaRepository] for request
 *
 * @param database database
 * @property api interface for service request of manga
 * @property dispatcher dispatcher for io  flow
 */
class MangaRepositoryImpl @Inject constructor(
    database: MangaDatabase,
    private val api: MangaApi,
    @IoDispatcher
    private val dispatcher: CoroutineDispatcher
) : MangaRepository {

    private val dao = database.mangaDao()

    override suspend fun lastUpdatedMangas(): List<UpdatedManga> = withContext(dispatcher) {
        api.lastUpdatedMangas().map { it.toDomain() }
    }

    override suspend fun searchMangaByName(mangaName: String): List<UpdatedManga> = withContext(dispatcher) {
        api.searchMangaByName(mangaName).map { it.toDomain() }
    }

    override fun popularManga(): Flow<PagingData<UpdatedManga>> = paging()

    override fun categoryManga(categoryId: String): Flow<PagingData<UpdatedManga>> = categoryPaging(categoryId)

    override suspend fun mangaDetail(mangaId: String): Manga = withContext(dispatcher) {
        val dbData = dao.getManga(mangaId)
        val chapters = dao.getChapters(mangaId)
        val apiData = api.mangaDetail(mangaId).toDomain()

        if (dbData != null && chapters != null) apiData.enrichDbData(dbData, chapters)
        else apiData
    }

    override suspend fun mangaChapter(mangaId: String, chapterId: String): List<String> = withContext(dispatcher) {
        api.mangaPages(mangaId, chapterId)
    }

    override suspend fun updateMangaStatus(manga: Manga) = withContext(dispatcher) {
        dao.putManga(manga.toDatabaseModel())
    }

    override suspend fun loadSavedMangas(): List<UpdatedManga> = withContext(dispatcher) {
        dao.getMangas()?.map { it.toDomain() } ?: listOf()
    }

    override suspend fun saveChapterInfo(mangaId: String, chapterId: String, wasRead: Boolean) = withContext(dispatcher) {
        dao.putChapter(
            ChaptersModel(
                id = compareMangaIdAndChapterIdToKey(mangaId, chapterId),
                mangaKey = mangaId,
                idWithoutMangaName = chapterId,
                wasRead = wasRead
            )
        )
    }

    private fun paging() = Pager(
        config = PagingConfig(100),
        pagingSourceFactory = { PagingDataSource(api = api) }
    ).flow

    private fun categoryPaging(categoryId: String) = Pager(
        config = PagingConfig(100),
        pagingSourceFactory = { CategoryDataSource(api = api, category = categoryId) }
    ).flow

    private fun compareMangaIdAndChapterIdToKey(mangaId: String, chapterId: String) = "${mangaId}_${chapterId}"
}