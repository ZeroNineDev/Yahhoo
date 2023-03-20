package com.zeroninedev.manga.presentation.detail.viewmodel

sealed class DetailMangaIntent {

    object UpdateRequest : DetailMangaIntent()

    object ShowMangaReadStatus : DetailMangaIntent()

    object SaveSelectedManga : DetailMangaIntent()

    data class LoadMangaInfo(val mangaId: String) : DetailMangaIntent()

    data class ShowChapterDetailInfo(val chapterId: String) : DetailMangaIntent()
}
