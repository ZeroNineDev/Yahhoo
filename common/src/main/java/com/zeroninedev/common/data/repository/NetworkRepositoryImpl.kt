package com.zeroninedev.common.data.repository

import com.zeroninedev.common.di.IoDispatcher
import com.zeroninedev.common.data.api.MangaApi
import com.zeroninedev.common.domain.NetworkRepository
import com.zeroninedev.common.domain.models.Manga
import com.zeroninedev.common.domain.models.UpdatedManga
import com.zeroninedev.common.domain.models.toDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val api: MangaApi,
    @IoDispatcher
    private val dispatcher: CoroutineDispatcher
) : NetworkRepository {

    override suspend fun lastUpdatedMangas(): List<UpdatedManga> = withContext(dispatcher){
        api.lastUpdatedMangas().map { it.toDomain() }
    }

    override suspend fun searchMangaByName(mangaName: String): List<UpdatedManga> = withContext(dispatcher){
        api.searchMangaByName(mangaName).map { it.toDomain() }
    }

    override suspend fun popularManga(): List<UpdatedManga> = withContext(dispatcher){
        api.popularMangas(1).mangas.map { it.toDomain() }
    }

    override suspend fun mangaDetail(mangaId: String): Manga = withContext(dispatcher){
        api.mangaDetail(mangaId).toDomain()
    }

    override suspend fun mangaChapter(mangaId: String, chapterId: String): List<String> = withContext(dispatcher){
        api.mangaPages(mangaId, chapterId)
    }
}