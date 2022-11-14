package com.zeroninedev.manga.presentation.screen

import androidx.compose.runtime.Composable
import com.zeroninedev.core_compose.components.text.ScreenTitleTextView
import com.zeroninedev.navigation.actions.Navigator

@Composable
fun LastUpdatedMangaScreen(internalNavigation: Navigator, mainNavigation: Navigator) {
    ScreenTitleTextView(text = "LAST")
}