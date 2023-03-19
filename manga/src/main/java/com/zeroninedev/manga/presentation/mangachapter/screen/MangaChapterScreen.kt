package com.zeroninedev.manga.presentation.mangachapter.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.zeroninedev.core_compose.components.screen.ErrorScreen
import com.zeroninedev.core_compose.components.screen.LoadingScreen
import com.zeroninedev.manga.presentation.mangachapter.screen.MangaScreenState.Error
import com.zeroninedev.manga.presentation.mangachapter.screen.MangaScreenState.Loading
import com.zeroninedev.manga.presentation.mangachapter.screen.MangaScreenState.ScrollSuccess
import com.zeroninedev.manga.presentation.mangachapter.screen.MangaScreenState.SwitchSuccess
import com.zeroninedev.manga.presentation.mangachapter.screen.MangaScreenState.TapSuccess
import com.zeroninedev.manga.presentation.mangachapter.view.MangaChapterTapView
import com.zeroninedev.manga.presentation.mangachapter.view.MangaChapterScrollView
import com.zeroninedev.manga.presentation.mangachapter.view.MangaChapterSwipeView
import com.zeroninedev.manga.presentation.mangachapter.viewmodel.MangaChapterIntent
import com.zeroninedev.manga.presentation.mangachapter.viewmodel.NewMangaChapterViewModel

/**
 * Reader manga chapter screen
 *
 * @param viewModel ViewModel for current screen
 */
@Composable
internal fun MangaChapterScreen(viewModel: NewMangaChapterViewModel) {
    when (val result = viewModel.screenState.collectAsState().value) {
        is Error -> {
            ErrorScreen(errorMessage = result.exception) {
                viewModel.processIntent(MangaChapterIntent.UpdateResponse)
            }
        }
        is Loading -> {
            LoadingScreen()
        }
        is ScrollSuccess -> {
            MangaChapterScrollView(
                chapterPage = result.data,
                chapterName = result.chapterName,
                prevPart = { viewModel.processIntent(MangaChapterIntent.LoadPreviousChapter) },
                nextPart = { viewModel.processIntent(MangaChapterIntent.LoadNextChapter) },
                afterHalfPart = { viewModel.processIntent(MangaChapterIntent.HalfPagesScrolled) },
                onErrorAction = { viewModel.processIntent(MangaChapterIntent.SendErrorMessage(it)) }
            )
        }
        is SwitchSuccess -> {
            MangaChapterSwipeView(
                chapterPageUrl = result.data,
                currentPage = result.currentPage,
                maxPage = result.maxPage,
                chapterName = result.chapterName,
                prevPart = { viewModel.processIntent(MangaChapterIntent.LoadPreviousPage) },
                nextPart = { viewModel.processIntent(MangaChapterIntent.LoadNextPage) },
                onErrorAction = { viewModel.processIntent(MangaChapterIntent.SendErrorMessage(it)) }
            )
        }
        is TapSuccess -> {
            MangaChapterTapView(
                chapterPageUrl = result.data,
                currentPage = result.currentPage,
                maxPage = result.maxPage,
                chapterName = result.chapterName,
                prevPart = { viewModel.processIntent(MangaChapterIntent.LoadPreviousPage) },
                nextPart = { viewModel.processIntent(MangaChapterIntent.LoadNextPage) },
                onErrorAction = { viewModel.processIntent(MangaChapterIntent.SendErrorMessage(it)) }
            )
        }
    }
}