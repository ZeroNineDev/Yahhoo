package com.zeroninedev.manga.presentation.mangasetting.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.zeroninedev.core_compose.components.button.RadioButtonComponent
import com.zeroninedev.core_compose.components.text.SubTitleText

/**
 * View for setting screen
 *
 * @param isMangaSwitchBySwipe
 * @param onMangaSwitchBySwipeChange
 */
@Composable
internal fun SettingMangaView(
    isMangaSwitchBySwipe: Boolean,
    onMangaSwitchBySwipeChange: (Boolean) -> Unit
) {
    LazyColumn {

        item {
            val swipe = 0 to stringResource(id = com.zeroninedev.core_compose.R.string.manga_switch_by_swipe)
            val tap = 1 to stringResource(id = com.zeroninedev.core_compose.R.string.manga_switch_by_tap)
            SubTitleText(text = stringResource(id = com.zeroninedev.core_compose.R.string.manga_switch_swipe_text))
            RadioButtonComponent(
                list = listOf(tap, swipe),
                selectedItem = if (isMangaSwitchBySwipe) swipe.second else tap.second
            ) {
                onMangaSwitchBySwipeChange(it == swipe.first)
            }
        }
        
    }
}