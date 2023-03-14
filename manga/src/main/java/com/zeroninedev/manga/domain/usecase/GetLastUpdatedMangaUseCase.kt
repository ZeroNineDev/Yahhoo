package com.zeroninedev.manga.domain.usecase

import com.zeroninedev.common.domain.MangaRepository
import com.zeroninedev.common.domain.models.UpdatedManga
import javax.inject.Inject

/**
 * Use case for load last updated manga list
 *
 * @property repository interface for request in manga service
 */
internal class GetLastUpdatedMangaUseCase @Inject constructor(private val repository: MangaRepository) {

    /**
     * @return list of last updated mangas
     */
    suspend operator fun invoke(): List<UpdatedManga> = repository.lastUpdatedMangas()
}