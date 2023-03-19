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
    data class ScrollSuccess(val data: List<String>, val chapterName: String) : MangaScreenState()

    /**
     * State when info was successful loaded
     *
     * @property data screen info
     */
    data class TapSuccess(val data: String, val currentPage: Int, val maxPage: Int, val chapterName: String) : MangaScreenState()

    /**
     * State when info was successful loaded
     *
     * @property data screen info
     */
    data class SwitchSuccess(val data: String, val currentPage: Int, val maxPage: Int, val chapterName: String) : MangaScreenState()

    /**
     * State when info was`t loaded
     * or loaded with error
     *
     * @property exception
     */
    data class Error(val exception: String) : MangaScreenState()
}