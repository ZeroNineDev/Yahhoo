package com.zeroninedev.common.data.repository

import com.zeroninedev.common.constants.Constants
import com.zeroninedev.common.data.sharedprefs.SharedPreferencesProvider
import com.zeroninedev.common.domain.SettingRepository
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

    private var isMangaFlip: Boolean = sharedPreferencesProvider.getBoolean(Constants.MANGA_PAGES_SWITCHING)

    override fun getIsMangaFlipThrough(): Boolean = isMangaFlip

    override fun setMangaFlipThrough(state: Boolean) {
        isMangaFlip = state
        sharedPreferencesProvider.putBoolean(Constants.MANGA_PAGES_SWITCHING, state)
    }
}