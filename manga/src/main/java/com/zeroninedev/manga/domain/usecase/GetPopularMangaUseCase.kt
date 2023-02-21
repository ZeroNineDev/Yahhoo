package com.zeroninedev.manga.domain.usecase

import androidx.paging.PagingData
import com.zeroninedev.common.domain.NetworkRepository
import com.zeroninedev.common.domain.models.UpdatedManga
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetPopularMangaUseCase @Inject constructor(private val repository: NetworkRepository) {

    operator fun invoke(): Flow<PagingData<UpdatedManga>> = repository.popularManga()
}