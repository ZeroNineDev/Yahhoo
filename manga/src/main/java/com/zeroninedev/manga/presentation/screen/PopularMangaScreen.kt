package com.zeroninedev.manga.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import com.zeroninedev.core_compose.components.text.ScreenTitleTextView
import com.zeroninedev.navigation.actions.Navigator

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
internal fun PopularMangaScreen(internalNavigation: Navigator, mainNavigation: Navigator) {
    ScreenTitleTextView(text = "POPULAR")
}