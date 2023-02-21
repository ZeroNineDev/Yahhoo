package com.zeroninedev.manga.presentation.search.screen

import com.zeroninedev.common.domain.models.UpdatedManga

internal sealed class SearchScreenState {

    object Loading : SearchScreenState()

    object Empty : SearchScreenState()

    data class Success(val data: List<UpdatedManga>) : SearchScreenState()

    data class Error(val exception: String) : SearchScreenState()
}