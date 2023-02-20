package com.zeroninedev.manga.domain.usecase

import com.zeroninedev.manga.domain.StateRepository
import com.zeroninedev.manga.presentation.mangachapter.model.ChapterState
import javax.inject.Inject

internal class GetNextChapterUseCase @Inject constructor(private val repository: StateRepository) {

    operator fun invoke(
        chapter: String,
        chapterToLoad: ChapterState
    ): String {
        val chapters = repository.getChapters()
        return chapters[chapters.indexOf(chapter) + chapterToLoad.value]
    }
}