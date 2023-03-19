package com.zeroninedev.manga.domain.mapper

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.zeroninedev.common.settingsmodel.SwitchPages
import com.zeroninedev.common.settingsmodel.SwitchPages.SCROLL_TO_NEXT

@Composable
internal fun SwitchPages.toUiString() = when (this) {
    SwitchPages.TAP_TO_NEXT -> stringResource(id = com.zeroninedev.core_compose.R.string.manga_switch_by_tap)
    SwitchPages.SWIPE_TO_NEXT -> stringResource(id = com.zeroninedev.core_compose.R.string.manga_switch_by_swipe)
    SCROLL_TO_NEXT -> stringResource(id = com.zeroninedev.core_compose.R.string.manga_switch_by_scroll)
}