package com.zeroninedev.manga.presentation.mangasetting.screen

import com.zeroninedev.common.settingsmodel.SwitchPages

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
     * @property mangaSwitch screen info
     */
    data class Success(val mangaSwitch: SwitchPages) : SettingScreenState()

    /**
     * State when info was`t loaded
     * or loaded with error
     *
     * @property exception
     */
    data class Error(val exception: String) : SettingScreenState()
}