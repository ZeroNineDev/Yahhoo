package com.zeroninedev.manga.domain.usecase

import com.zeroninedev.common.domain.MangaRepository
import com.zeroninedev.common.domain.models.Manga
import javax.inject.Inject

/**
 * Use case for load manga with detailed information
 *
 * @property repository interface for request in manga service
 */
internal class GetDetailMangaUseCase @Inject constructor(private val repository: MangaRepository) {

    /**
     * @param mangaId manga id
     * @return manga detailed information
     */
    suspend operator fun invoke(mangaId: String): Manga = repository.mangaDetail(mangaId)
}