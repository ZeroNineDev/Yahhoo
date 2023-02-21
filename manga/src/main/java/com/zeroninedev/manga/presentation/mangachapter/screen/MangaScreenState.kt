package com.zeroninedev.manga.presentation.mangachapter.screen

internal sealed class MangaScreenState {
    object Loading : MangaScreenState()
    data class Success(val data: List<String>) : MangaScreenState()
    data class Error(val exception: Throwable) : MangaScreenState()
}