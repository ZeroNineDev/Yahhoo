package com.zeroninedev.common.data.repository

import com.zeroninedev.common.constants.Constants
import com.zeroninedev.common.data.sharedprefs.SharedPreferencesProvider
import com.zeroninedev.common.domain.SettingRepository
import com.zeroninedev.common.settingsmodel.SwitchPages
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementation of [SettingRepository]
 *
 * @property sharedPreferencesProvider
 */
@Singleton
class SettingRepositoryImpl @Inject constructor(
    private val sharedPreferencesProvider: SharedPreferencesProvider
) : SettingRepository {

    override var mangaSwitchPages: SwitchPages = SwitchPages.valueOf(sharedPreferencesProvider.getString(Constants.MANGA_PAGES_SWITCHING, SwitchPages.TAP_TO_NEXT.name))
        set(value) {
            field = value
            sharedPreferencesProvider.putString(Constants.MANGA_PAGES_SWITCHING, value.name)
        }
}