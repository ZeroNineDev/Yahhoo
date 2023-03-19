package com.zeroninedev.manga.presentation.detail.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import com.zeroninedev.core_compose.components.screen.ErrorScreen
import com.zeroninedev.core_compose.components.screen.LoadingScreen
import com.zeroninedev.manga.presentation.detail.view.DetailMangaView
import com.zeroninedev.manga.presentation.detail.view.MangaStatusBottomSheetView
import com.zeroninedev.manga.presentation.detail.viewmodel.DetailMangaViewModel
import com.zeroninedev.navigation.actions.Navigator
import com.zeroninedev.navigation.destination.Screen.CategoryScreen
import com.zeroninedev.navigation.destination.Screen.MangaChapterScreen

/**
 * Detail manga screen
 *
 * @param navigator main navigator
 * @param viewModel ViewModel for current screen
 */
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
internal fun DetailMangaScreen(
    navigator: Navigator,
    viewModel: DetailMangaViewModel,
) {
    when (val result = viewModel.screenState.collectAsState().value) {
        is DetailScreenState.Error -> {
            ErrorScreen(errorMessage = result.exception) { viewModel.updateRequest() }
        }
        is DetailScreenState.Loading -> {
            LoadingScreen()
        }
        is DetailScreenState.Success -> {
            if (result.data != null) {
                DetailMangaView(
                    manga = result.data,
                    onChapterLongClick = { viewModel.showWasReadStateBottomSheet(it) },
                    onChapterClick = { chapterId ,chapterName ->
                        viewModel.saveChapters(result.data.chapters.map { it.id.orEmpty() to it.title.orEmpty() })
                        navigator.navigate(MangaChapterScreen.getRoute(result.data.id.orEmpty(), chapterId, chapterName))
                    },
                    onChangeStatus = { viewModel.showMangaStatusBottomSheet() },
                    onChipClick = { navigator.navigate(CategoryScreen.getRoute(it.name.orEmpty(), it.id.orEmpty())) }
                )
            }
        }
    }

    when (val result = viewModel.bottomSheet.collectAsState().value) {
        is BottomSheetState.None -> Unit
        is BottomSheetState.ReadStatus -> {
            MangaStatusBottomSheetView(
                currentMangaStatus = result.data,
                onChangeStatus = { viewModel.saveUpdatedInfo(it) },
                onDismiss = { viewModel.hideBottomSheet() }
            )
        }
        is BottomSheetState.WasReadState -> {
            MangaStatusBottomSheetView(
                onChangeStatus = { viewModel.saveUpdatedChapter(result.data, it) },
                onDismiss = { viewModel.hideBottomSheet() }
            )
        }
    }
}