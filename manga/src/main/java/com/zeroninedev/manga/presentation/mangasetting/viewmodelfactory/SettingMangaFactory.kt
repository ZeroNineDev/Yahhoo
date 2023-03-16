package com.zeroninedev.manga.presentation.mangasetting.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zeroninedev.manga.domain.setting.GetMangaSwitchSettingUseCase
import com.zeroninedev.manga.domain.setting.SetMangaSwitchSettingUseCase
import com.zeroninedev.manga.presentation.mangasetting.viewmodel.SettingMangaViewModel
import javax.inject.Inject

internal class SettingMangaFactory @Inject constructor(
    private val setMangaSwitchSettingUseCase: SetMangaSwitchSettingUseCase,
    private val getMangaSwitchSettingUseCase: GetMangaSwitchSettingUseCase
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        SettingMangaViewModel(setMangaSwitchSettingUseCase, getMangaSwitchSettingUseCase) as T
}