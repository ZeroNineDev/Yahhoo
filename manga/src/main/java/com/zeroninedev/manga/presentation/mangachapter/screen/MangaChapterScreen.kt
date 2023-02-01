package com.zeroninedev.manga.presentation.mangachapter.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.zeroninedev.core_compose.components.image.MangaPageView
import com.zeroninedev.manga.presentation.mangachapter.viewmodel.MangaChapterViewModel
import com.zeroninedev.navigation.actions.Navigator

@Composable
internal fun MangaChapterScreen(
    navigator: Navigator,
    viewModel: MangaChapterViewModel,
) {
    val screen = viewModel.screenState.collectAsState().value
    if (screen != null) {
        LazyColumn {
            items(screen) {
                MangaPageView(it)
            }
        }
    }
}