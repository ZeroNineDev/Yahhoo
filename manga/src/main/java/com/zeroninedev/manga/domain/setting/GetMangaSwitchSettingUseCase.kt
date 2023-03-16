package com.zeroninedev.manga.domain.setting

import com.zeroninedev.common.domain.SettingRepository
import javax.inject.Inject

/**
 * Use case for get from repository state flip trough manga pages
 *
 * @property settingRepository repository with  settings
 */
internal class GetMangaSwitchSettingUseCase @Inject constructor(
    private val settingRepository: SettingRepository
) {

    operator fun invoke(): Boolean = settingRepository.getIsMangaFlipThrough()
}