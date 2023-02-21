package com.zeroninedev.manga.presentation.detail.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.zeroninedev.core_compose.components.screen.ErrorScreen
import com.zeroninedev.core_compose.components.screen.LoadingScreen
import com.zeroninedev.manga.presentation.detail.view.DetailMangaView
import com.zeroninedev.manga.presentation.detail.viewmodel.DetailMangaViewModel
import com.zeroninedev.navigation.actions.Navigator
import com.zeroninedev.navigation.destination.Screen.MangaChapterScreen

@ExperimentalAnimationApi
@Composable
internal fun DetailMangaScreen(
    navigator: Navigator,
    viewModel: DetailMangaViewModel,
) {
    when (val result = viewModel.screenState.collectAsState().value) {
        is DetailScreenState.Error -> {
            ErrorScreen(result.exception) { viewModel.updateRequest() }
        }
        is DetailScreenState.Loading -> {
            LoadingScreen()
        }
        is DetailScreenState.Success -> {
            if (result.data != null) {
                DetailMangaView(manga = result.data) { chapterId ->
                    viewModel.saveChapters(result.data.chapters.map { it.id.orEmpty() })
                    navigator.navigate("${MangaChapterScreen.ROUTE}/${result.data.id}/${chapterId}")
                }
            }

        }
    }
}