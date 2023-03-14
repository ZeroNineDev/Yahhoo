package com.zeroninedev.manga.presentation.search.screen

import com.zeroninedev.common.domain.models.UpdatedManga

/**
 * States of search manga screen
 *
 */
internal sealed class SearchScreenState {

    /** State when info loading */
    object Loading : SearchScreenState()

    /** State when info empty */
    object Empty : SearchScreenState()

    /**
     * State when info was successful loaded
     *
     * @property data screen info
     */
    data class Success(val data: List<UpdatedManga>) : SearchScreenState()

    /**
     * State when info was`t loaded
     * or loaded with error
     *
     * @property exception
     */
    data class Error(val exception: String) : SearchScreenState()
}