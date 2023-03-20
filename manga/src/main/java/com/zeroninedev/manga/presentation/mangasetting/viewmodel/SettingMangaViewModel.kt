package com.zeroninedev.manga.presentation.mangasetting.viewmodel

import androidx.lifecycle.viewModelScope
import com.zeroninedev.common.base.BaseViewModel
import com.zeroninedev.common.settingsmodel.SwitchPages
import com.zeroninedev.manga.domain.setting.GetMangaSwitchSettingUseCase
import com.zeroninedev.manga.domain.setting.SetMangaSwitchSettingUseCase
import com.zeroninedev.manga.presentation.mangasetting.screen.SettingScreenState
import com.zeroninedev.manga.presentation.mangasetting.viewmodel.SettingIntent.ChangeMangaSwitch
import com.zeroninedev.manga.presentation.mangasetting.viewmodel.SettingIntent.LoadSettings
import com.zeroninedev.manga.presentation.mangasetting.viewmodel.SettingIntent.UpdateRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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
) : BaseViewModel<SettingScreenState>(SettingScreenState.Loading) {

    fun processIntent(intent: SettingIntent) = when(intent) {
        is ChangeMangaSwitch -> onChangeMangaSwitch(intent.param)
        LoadSettings -> loadSettings()
        UpdateRequest -> loadSettings()
    }

    private fun loadSettings() {
        viewModelScope.launch {
            _screenState.value = SettingScreenState.Loading
            delay(100)
            _screenState.value = SettingScreenState.Success(
                mangaSwitch = getMangaSwitchSettingUseCase()
            )
        }
    }

    private fun onChangeMangaSwitch(state: SwitchPages) {
        setMangaSwitchSettingUseCase(state)
    }
}