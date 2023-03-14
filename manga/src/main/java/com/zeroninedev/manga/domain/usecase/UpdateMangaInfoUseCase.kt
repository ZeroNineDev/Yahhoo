package com.zeroninedev.manga.domain.usecase

import com.zeroninedev.common.domain.MangaRepository
import com.zeroninedev.common.domain.models.Manga
import javax.inject.Inject

/**
 * Use case for update manga information
 *
 * @property repository interface for request in manga service
 */
internal class UpdateMangaInfoUseCase @Inject constructor(private val repository: MangaRepository) {

    /**
     * @param manga manga to update info
     */
    suspend operator fun invoke(manga: Manga) = repository.updateMangaStatus(manga)
}