package com.zeroninedev.manga.presentation.detail.screen

import com.zeroninedev.common.domain.models.Manga

/**
 * States of detail screen
 *
 */
internal sealed class DetailScreenState {

    /** State when info loading */
    object Loading : DetailScreenState()

    /**
     * State when info was successful loaded
     *
     * @property data screen info
     */
    data class Success(val data: Manga?) : DetailScreenState()

    /**
     * State when info was`t loaded
     * or loaded with error
     *
     * @property exception
     */
    data class Error(val exception: String) : DetailScreenState()
}