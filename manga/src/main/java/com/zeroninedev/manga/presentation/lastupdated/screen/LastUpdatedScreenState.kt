package com.zeroninedev.manga.presentation.lastupdated.screen

import com.zeroninedev.common.domain.models.UpdatedManga

/**
 * States of last updated manga screen
 *
 */
internal sealed class LastUpdatedScreenState {

    /** State when info loading */
    object Loading : LastUpdatedScreenState()

    /**
     * State when info was successful loaded
     *
     * @property data screen info
     */
    data class Success(val data: List<UpdatedManga>) : LastUpdatedScreenState()

    /**
     * State when info was`t loaded
     * or loaded with error
     *
     * @property exception
     */
    data class Error(val exception: String) : LastUpdatedScreenState()
}