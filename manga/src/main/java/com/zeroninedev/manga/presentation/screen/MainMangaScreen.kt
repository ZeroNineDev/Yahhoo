package com.zeroninedev.manga.presentation.screen

import androidx.compose.runtime.Composable
import com.zeroninedev.manga.presentation.view.MainMangaView
import com.zeroninedev.navigation.actions.Navigator

@Composable
fun MainMangaScreen(navigator: Navigator) {
    MainMangaView(navigator)
}