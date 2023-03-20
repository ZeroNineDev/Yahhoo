package com.zeroninedev.manga.presentation.mangasetting.viewmodel

import com.zeroninedev.common.settingsmodel.SwitchPages

internal sealed class SettingIntent {

    object UpdateRequest : SettingIntent()

    object LoadSettings : SettingIntent()

    data class ChangeMangaSwitch(val param: SwitchPages) : SettingIntent()
}