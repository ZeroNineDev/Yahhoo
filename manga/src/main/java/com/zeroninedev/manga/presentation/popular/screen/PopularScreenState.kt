package com.zeroninedev.manga.presentation.popular.screen

import androidx.paging.PagingData
import com.zeroninedev.common.domain.models.UpdatedManga
import kotlinx.coroutines.flow.Flow

internal sealed class PopularScreenState {

    object Loading : PopularScreenState()

    data class Success(val data: Flow<PagingData<UpdatedManga>>) : PopularScreenState()

    data class Error(val exception: String) : PopularScreenState()
}