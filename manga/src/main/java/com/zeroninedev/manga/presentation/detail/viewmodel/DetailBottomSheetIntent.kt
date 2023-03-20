package com.zeroninedev.manga.presentation.detail.viewmodel

import com.zeroninedev.common.domain.models.MangaReadStatus

sealed class DetailBottomSheetIntent {

    object DismissBottomSheet: DetailBottomSheetIntent()

    data class SelectMangaStatus(val mangaReadStatus: MangaReadStatus) : DetailBottomSheetIntent()

    data class ProcessMangaChapter(val chapterId: String, val state: Boolean) : DetailBottomSheetIntent()
}
