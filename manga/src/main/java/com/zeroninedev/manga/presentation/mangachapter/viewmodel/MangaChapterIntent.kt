package com.zeroninedev.manga.presentation.mangachapter.viewmodel

import com.zeroninedev.navigation.actions.Navigator

internal sealed class MangaChapterIntent {

    object LoadNextChapter: MangaChapterIntent()

    object HalfPagesScrolled: MangaChapterIntent()

    object LoadPreviousChapter: MangaChapterIntent()

    object LoadNextPage: MangaChapterIntent()

    object LoadPreviousPage: MangaChapterIntent()

    object UpdateResponse: MangaChapterIntent()

    data class SendErrorMessage(val message: String?): MangaChapterIntent()

    data class SetNavigator(val navigator: Navigator): MangaChapterIntent()

    data class LoadMangaChapters(val mangaId: String, val chapterId: String, val chapterName: String): MangaChapterIntent()
}