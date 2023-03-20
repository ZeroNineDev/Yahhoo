package com.zeroninedev.manga.presentation.saved.viewmodel

import com.zeroninedev.common.domain.models.MangaReadStatus

internal sealed class SavedMangaIntent {

    object UpdateResponse : SavedMangaIntent()

    object LoadManga : SavedMangaIntent()

    data class ChangeFilterStatus(val status: MangaReadStatus) : SavedMangaIntent()
}