package com.zeroninedev.manga.presentation.mangasetting.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.zeroninedev.core_compose.components.screen.ErrorScreen
import com.zeroninedev.core_compose.components.screen.LoadingScreen
import com.zeroninedev.manga.presentation.mangasetting.screen.SettingScreenState.Error
import com.zeroninedev.manga.presentation.mangasetting.screen.SettingScreenState.Loading
import com.zeroninedev.manga.presentation.mangasetting.screen.SettingScreenState.Success
import com.zeroninedev.manga.presentation.mangasetting.view.SettingMangaView
import com.zeroninedev.manga.presentation.mangasetting.viewmodel.SettingIntent
import com.zeroninedev.manga.presentation.mangasetting.viewmodel.SettingMangaViewModel

/**
 * Setting screen
 *
 * @param viewModel ViewModel for current screen
 */
@Composable
internal fun SettingMangaScreen(
    viewModel: SettingMangaViewModel
) {
    LaunchedEffect(key1 = null) { viewModel.processIntent(SettingIntent.LoadSettings) }

    when (val result = viewModel.screenState.collectAsState().value) {
        is Error -> {
            ErrorScreen(errorMessage = result.exception) { viewModel.processIntent(SettingIntent.UpdateRequest) }
        }
        is Loading -> {
            LoadingScreen()
        }
        is Success -> {
            SettingMangaView(mangaSwitch = result.mangaSwitch) {
                viewModel.processIntent(SettingIntent.ChangeMangaSwitch(it))
            }
        }
    }
}