package com.zeroninedev.manga.presentation.detail.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.zeroninedev.manga.presentation.detail.view.DetailMangaView
import com.zeroninedev.manga.presentation.detail.viewmodel.DetailMangaViewModel
import com.zeroninedev.navigation.actions.Navigator
import com.zeroninedev.navigation.destination.Screen.MangaChapterScreen

@Composable
internal fun DetailMangaScreen(
    navigator: Navigator,
    viewModel: DetailMangaViewModel,
) {
    val screen = viewModel.screenState.collectAsState().value
    if (screen != null) {
        DetailMangaView(manga = screen) { chapterId ->
            navigator.navigate("${MangaChapterScreen.ROUTE}/${screen.id}/${chapterId}")
        }
    }
}