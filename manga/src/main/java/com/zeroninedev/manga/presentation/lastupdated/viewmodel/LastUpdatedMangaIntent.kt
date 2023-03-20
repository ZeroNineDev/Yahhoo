package com.zeroninedev.manga.presentation.lastupdated.viewmodel

internal sealed class LastUpdatedMangaIntent {

    object UpdateResponse: LastUpdatedMangaIntent()

    object LoadManga: LastUpdatedMangaIntent()
}
