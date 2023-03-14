package com.zeroninedev.manga.presentation.category.screen

import androidx.paging.PagingData
import com.zeroninedev.common.domain.models.UpdatedManga
import kotlinx.coroutines.flow.Flow

/**
 * States of popular manga screen
 *
 */
internal sealed class CategoryScreenState {

    /** State when info loading */
    object Loading : CategoryScreenState()

    /**
     * State when info was successful loaded
     *
     * @property data screen info
     */
    data class Success(val data: Flow<PagingData<UpdatedManga>>, val name: String) : CategoryScreenState()

    /**
     * State when info was`t loaded
     * or loaded with error
     *
     * @property exception
     */
    data class Error(val exception: String) : CategoryScreenState()
}