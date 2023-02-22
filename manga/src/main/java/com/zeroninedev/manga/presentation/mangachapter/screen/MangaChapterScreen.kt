package com.zeroninedev.manga.presentation.mangachapter.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.zeroninedev.core_compose.components.screen.ErrorScreen
import com.zeroninedev.core_compose.components.screen.LoadingScreen
import com.zeroninedev.manga.presentation.mangachapter.view.MangaChapterView
import com.zeroninedev.manga.presentation.mangachapter.viewmodel.MangaChapterViewModel
import com.zeroninedev.navigation.actions.Navigator

/**
 * Reader manga chapter screen
 *
 * @param navigator main navigator
 * @param viewModel ViewModel for current screen
 */
@Composable
internal fun MangaChapterScreen(
    navigator: Navigator,
    viewModel: MangaChapterViewModel,
) {
    when (val result = viewModel.screenState.collectAsState().value) {
        is MangaScreenState.Error -> {
            ErrorScreen(errorMessage = result.exception) { viewModel.updateRequest() }
        }
        is MangaScreenState.Loading -> {
            LoadingScreen()
        }
        is MangaScreenState.Success -> {
            MangaChapterView(
                chapterPage = result.data,
                prevPart = { viewModel.loadPrevChapter() },
                nextPart = { viewModel.loadNextChapter() },
                onErrorAction = { viewModel.sendImageError(it) }
            )
        }
    }
}