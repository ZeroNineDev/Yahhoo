package com.zeroninedev.manga.presentation.mangasetting.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.zeroninedev.common.settingsmodel.SwitchPages
import com.zeroninedev.common.settingsmodel.switchPagesMapWithKey
import com.zeroninedev.core_compose.components.button.RadioButtonComponent
import com.zeroninedev.core_compose.components.text.SubTitleText
import com.zeroninedev.manga.domain.mapper.toUiString

/**
 * View for setting screen
 *
 * @param mangaSwitch
 * @param onMangaSwitchListener
 */
@Composable
internal fun SettingMangaView(
    mangaSwitch: SwitchPages,
    onMangaSwitchListener: (SwitchPages) -> Unit
) {
    LazyColumn {

        item {
            val map = switchPagesMapWithKey()

            SubTitleText(text = stringResource(id = com.zeroninedev.core_compose.R.string.manga_switch_swipe_text))
            RadioButtonComponent(
                items = map.mapValues { it.value.toUiString() },
                selectedItem = mangaSwitch.key
            ) { key ->
                map[key]?.let { onMangaSwitchListener(it) }
            }
        }
    }
}