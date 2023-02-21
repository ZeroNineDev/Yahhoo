package com.zeroninedev.manga.presentation.lastupdated.screen

import com.zeroninedev.common.domain.models.UpdatedManga

internal sealed class LastUpdatedScreenState {

    object Loading : LastUpdatedScreenState()

    data class Success(val data: List<UpdatedManga>) : LastUpdatedScreenState()

    data class Error(val exception: String) : LastUpdatedScreenState()
}