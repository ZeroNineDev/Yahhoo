package com.zeroninedev.manga.presentation.popular.viewmodel

internal sealed class PopularMangaIntent {

    object UpdateResponse: PopularMangaIntent()

    object LoadManga: PopularMangaIntent()
}