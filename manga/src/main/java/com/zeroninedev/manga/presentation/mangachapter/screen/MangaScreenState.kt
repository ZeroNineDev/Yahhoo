package com.zeroninedev.manga.presentation.mangachapter.screen

/**
 * States of reader chapter screen
 *
 */
internal sealed class MangaScreenState {

    /** State when info loading */
    object Loading : MangaScreenState()

    /**
     * State when info was successful loaded
     *
     * @property data screen info
     */
    data class Success(val data: List<String>, val isMangaSwitchSwipe: Boolean) : MangaScreenState()

    /**
     * State when info was`t loaded
     * or loaded with error
     *
     * @property exception
     */
    data class Error(val exception: String) : MangaScreenState()
}