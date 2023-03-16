package com.zeroninedev.manga.presentation.mangasetting.screen

/**
 * States of last updated manga screen
 *
 */
internal sealed class SettingScreenState {

    /** State when info loading */
    object Loading : SettingScreenState()

    /**
     * State when info was successful loaded
     *
     * @property isMangaSwitchSwipe screen info
     */
    data class Success(val isMangaSwitchSwipe: Boolean) : SettingScreenState()

    /**
     * State when info was`t loaded
     * or loaded with error
     *
     * @property exception
     */
    data class Error(val exception: String) : SettingScreenState()
}