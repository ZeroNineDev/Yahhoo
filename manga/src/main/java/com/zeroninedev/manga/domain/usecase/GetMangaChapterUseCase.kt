package com.zeroninedev.manga.domain.usecase

import com.zeroninedev.common.domain.NetworkRepository
import javax.inject.Inject

internal class GetMangaChapterUseCase @Inject constructor(private val repository: NetworkRepository) {

    suspend operator fun invoke(mangaId: String, chapterId: String): List<String> =
        repository.mangaChapter(mangaId = mangaId, chapterId = chapterId)
}