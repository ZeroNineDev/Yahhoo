package com.zeroninedev.manga.domain.usecase

import com.zeroninedev.common.domain.MangaRepository
import javax.inject.Inject

/**
 * Use case for was read manga chapter
 *
 * @property repository interface manga responses
 */
internal class UpdateChapterInfoUseCase @Inject constructor(private val repository: MangaRepository) {

    /**
     * @param chapterId current chapter
     * @param mangaId current manga
     * @param wasRead state was read
     */
    suspend operator fun invoke(chapterId: String, mangaId: String, wasRead: Boolean = true) {
        repository.saveChapterInfo(mangaId = mangaId, chapterId = chapterId, wasRead = wasRead)
    }
}