package com.zeroninedev.manga.presentation.search.viewmodel

internal sealed class SearchMangaIntent {

    object ClearResponse : SearchMangaIntent()

    object UpdateResponse : SearchMangaIntent()

    data class Typing(val query: String) : SearchMangaIntent()
}