package com.zeroninedev.manga.domain.usecase

import com.zeroninedev.manga.domain.StateRepository
import com.zeroninedev.manga.presentation.mangachapter.model.ChapterState
import javax.inject.Inject

/**
 * Use case for load next chapter id
 *
 * @property repository interface storage process information
 */
internal class GetNextChapterUseCase @Inject constructor(private val repository: StateRepository) {

    /**
     * @param chapter current chapter
     * @param chapterToLoad direction to load next chapter
     * @return next chapter id
     */
    operator fun invoke(
        chapter: Pair<String, String>,
        chapterToLoad: ChapterState
    ): Pair<String, String> {
        val chapters = repository.chapters
        return chapters[chapters.indexOf(chapter) + chapterToLoad.value]
    }
}