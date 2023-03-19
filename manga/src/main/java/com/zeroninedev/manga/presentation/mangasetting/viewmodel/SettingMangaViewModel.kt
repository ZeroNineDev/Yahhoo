package com.zeroninedev.manga.presentation.mangasetting.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeroninedev.common.settingsmodel.SwitchPages
import com.zeroninedev.manga.domain.setting.GetMangaSwitchSettingUseCase
import com.zeroninedev.manga.domain.setting.SetMangaSwitchSettingUseCase
import com.zeroninedev.manga.presentation.mangasetting.screen.SettingScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View Model for last updated screen
 *
 * @property setMangaSwitchSettingUseCase
 * @property getMangaSwitchSettingUseCase
 */
@HiltViewModel
internal class SettingMangaViewModel @Inject constructor(
    private val setMangaSwitchSettingUseCase: SetMangaSwitchSettingUseCase,
    private val getMangaSwitchSettingUseCase: GetMangaSwitchSettingUseCase
) : ViewModel() {

    private val _screenState = MutableStateFlow<SettingScreenState>(SettingScreenState.Loading)
    val screenState = _screenState.asStateFlow()

    /**
     * Reload info about setting
     */
    fun updateRequest() {
        loadSettings()
    }

    /**
     * Load info about setting
     */
    fun loadSettings() {
        viewModelScope.launch {
            _screenState.value = SettingScreenState.Loading
            delay(100)
            _screenState.value = SettingScreenState.Success(
                mangaSwitch = getMangaSwitchSettingUseCase()
            )
        }
    }

    fun onChangeMangaSwitch(state: SwitchPages) {
        setMangaSwitchSettingUseCase(state)
    }
}