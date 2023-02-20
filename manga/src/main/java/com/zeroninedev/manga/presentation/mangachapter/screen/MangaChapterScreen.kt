package com.zeroninedev.manga.presentation.mangachapter.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.zeroninedev.manga.presentation.mangachapter.view.MangaChapterView
import com.zeroninedev.manga.presentation.mangachapter.viewmodel.MangaChapterViewModel
import com.zeroninedev.navigation.actions.Navigator

@Composable
internal fun MangaChapterScreen(
    navigator: Navigator,
    viewModel: MangaChapterViewModel,
) {
    when (val result = viewModel.screenState.collectAsState().value) {
        is MangaScreenState.Error -> {}
        is MangaScreenState.Loading -> {}
        is MangaScreenState.Success -> {
            MangaChapterView(
                chapterPage = result.data,
                prevPart = { viewModel.loadPrevChapter() },
                nextPart = { viewModel.loadNextChapter() }
            )
        }
    }
}