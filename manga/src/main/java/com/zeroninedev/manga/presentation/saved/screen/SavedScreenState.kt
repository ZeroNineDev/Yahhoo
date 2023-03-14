package com.zeroninedev.manga.presentation.saved.screen

import com.zeroninedev.common.domain.models.UpdatedManga

/**
 * States of saved manga screen
 *
 */
internal sealed class SavedScreenState {

    /** State when info loading */
    object Loading : SavedScreenState()

    /**
     * State when info was successful loaded
     *
     * @property data screen info
     */
    data class Success(val data: List<UpdatedManga>) : SavedScreenState()

    /**
     * State when info was`t loaded
     * or loaded with error
     *
     * @property exception
     */
    data class Error(val exception: String) : SavedScreenState()

    /**
     * State when info was successful loaded but empty
     *
     */
    object Empty : SavedScreenState()
}