package com.zeroninedev.manga.domain.setting

import com.zeroninedev.common.domain.SettingRepository
import javax.inject.Inject

/**
 * Use case for set manga swipe in setting
 *
 * @property settingRepository setting repository
 */
internal class SetMangaSwitchSettingUseCase @Inject constructor(
    private val settingRepository: SettingRepository
) {

    operator fun invoke(isSwipe: Boolean) = settingRepository.setMangaFlipThrough(isSwipe)
}