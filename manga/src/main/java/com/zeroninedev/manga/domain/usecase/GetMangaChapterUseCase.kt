package com.zeroninedev.manga.domain.usecase

import com.zeroninedev.common.domain.MangaRepository
import javax.inject.Inject

/**
 * Use case for load current chapter pages
 *
 * @property repository interface for request in manga service
 */
internal class GetMangaChapterUseCase @Inject constructor(private val repository: MangaRepository) {

    /**
     * @param mangaId manga id
     * @param chapterId chapter id
     * @return list of url pages in chapter
     */
    suspend operator fun invoke(mangaId: String, chapterId: String): List<String> =
        repository.mangaChapter(mangaId = mangaId, chapterId = chapterId)
}