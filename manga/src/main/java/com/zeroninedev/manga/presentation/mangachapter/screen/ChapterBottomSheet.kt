package com.zeroninedev.manga.presentation.mangachapter.screen


internal sealed class ChapterBottomSheet {

    object None : ChapterBottomSheet()

    data class ChoiceChapter(val data: List<Pair<String, String>>) : ChapterBottomSheet()

    data class ChoicePage(val maxPage: Int) : ChapterBottomSheet()
}