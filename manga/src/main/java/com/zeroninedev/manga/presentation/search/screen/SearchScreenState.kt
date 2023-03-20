package com.zeroninedev.manga.presentation.search.screen

import com.zeroninedev.common.domain.models.UpdatedManga

/**
 * States of search manga screen
 *
 */
internal sealed class SearchScreenState {

    /** State when info loading */
    data class Loading(val query: String = "") : SearchScreenState()

    /** State when info empty */
    data class Empty(val query: String = "") : SearchScreenState()

    /**
     * State when info was successful loaded
     *
     * @property data screen info
     */
    data class Success(val query: String, val data: List<UpdatedManga>) : SearchScreenState()

    /**
     * State when info was`t loaded
     * or loaded with error
     *
     * @property exception
     */
    data class Error(val query: String, val exception: String) : SearchScreenState()
}