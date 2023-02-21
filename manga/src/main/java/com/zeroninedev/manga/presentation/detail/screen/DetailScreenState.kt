package com.zeroninedev.manga.presentation.detail.screen

import com.zeroninedev.common.domain.models.Manga

internal sealed class DetailScreenState {

    object Loading : DetailScreenState()

    data class Success(val data: Manga?) : DetailScreenState()

    data class Error(val exception: String) : DetailScreenState()
}