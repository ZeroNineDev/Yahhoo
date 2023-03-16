package com.zeroninedev.common.domain

/**
 * Repository for work with settings
 *
 */
interface SettingRepository {

    /**
     * @return is manga swipe available
     */
    fun getIsMangaFlipThrough(): Boolean

    /**
     * Set swipe available
     *
     * @param state state
     */
    fun setMangaFlipThrough(state: Boolean)
}