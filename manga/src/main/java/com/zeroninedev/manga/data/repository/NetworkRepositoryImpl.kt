package com.zeroninedev.manga.data.repository

import com.zeroninedev.common.di.IoDispatcher
import com.zeroninedev.manga.data.api.MangaApi
import com.zeroninedev.manga.domain.NetworkRepository
import com.zeroninedev.manga.domain.models.UpdatedManga
import com.zeroninedev.manga.domain.models.toDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class NetworkRepositoryImpl @Inject constructor(
    private val api: MangaApi,
    @IoDispatcher
    private val dispatcher: CoroutineDispatcher
) : NetworkRepository {

    override suspend fun lastUpdatedMangas(): List<UpdatedManga> = withContext(dispatcher){
        api.lastUpdatedMangas().map { it.toDomain() }
    }
}