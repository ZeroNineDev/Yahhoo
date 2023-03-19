package com.zeroninedev.common.settingsmodel

enum class SwitchPages(val key: Int) {

    TAP_TO_NEXT(1),

    SWIPE_TO_NEXT(2),

    SCROLL_TO_NEXT(3),
}

fun switchPagesMapWithKey() = SwitchPages.values().associateBy { it.key }