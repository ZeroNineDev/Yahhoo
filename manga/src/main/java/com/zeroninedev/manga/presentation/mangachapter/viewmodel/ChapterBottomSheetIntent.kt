package com.zeroninedev.manga.presentation.mangachapter.viewmodel

internal sealed class ChapterBottomSheetIntent {

    object DismissBottomSheet : ChapterBottomSheetIntent()

    data class SelectPage(val page: Int) : ChapterBottomSheetIntent()

    data class SelectChapter(val data: Pair<String, String>) : ChapterBottomSheetIntent()
}