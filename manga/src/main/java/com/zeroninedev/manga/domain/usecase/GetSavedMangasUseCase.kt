package com.zeroninedev.manga.domain.usecase

import com.zeroninedev.common.domain.MangaRepository
import com.zeroninedev.common.domain.models.UpdatedManga
import javax.inject.Inject

/**
 * Use case for load saved manga list
 *
 * @property repository interface for request in manga service
 */
internal class GetSavedMangasUseCase @Inject constructor(private val repository: MangaRepository) {

    /**
     * @return list of saved mangas
     */
    suspend operator fun invoke(): List<UpdatedManga> = repository.loadSavedMangas()
}