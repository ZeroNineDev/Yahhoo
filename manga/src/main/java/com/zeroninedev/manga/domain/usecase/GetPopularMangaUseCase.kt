package com.zeroninedev.manga.domain.usecase

import androidx.paging.PagingData
import com.zeroninedev.common.domain.MangaRepository
import com.zeroninedev.common.domain.models.UpdatedManga
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for load popular manga list
 *
 * @property repository interface for request in manga service
 */
internal class GetPopularMangaUseCase @Inject constructor(private val repository: MangaRepository) {

    /**
     * @return flow of mangas with pagination
     */
    operator fun invoke(): Flow<PagingData<UpdatedManga>> = repository.popularManga()
}