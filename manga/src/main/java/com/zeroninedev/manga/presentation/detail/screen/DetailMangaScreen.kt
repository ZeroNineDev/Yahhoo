package com.zeroninedev.manga.presentation.detail.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import com.zeroninedev.common.domain.models.MangaReadStatus
import com.zeroninedev.core_compose.components.screen.ErrorScreen
import com.zeroninedev.core_compose.components.screen.LoadingScreen
import com.zeroninedev.manga.presentation.detail.view.DetailMangaView
import com.zeroninedev.manga.presentation.detail.view.MangaStatusBottomSheetView
import com.zeroninedev.manga.presentation.detail.viewmodel.DetailMangaViewModel
import com.zeroninedev.navigation.actions.Navigator
import com.zeroninedev.navigation.destination.Screen.MangaChapterScreen

/**
 * Detail manga screen
 *
 * @param navigator main navigator
 * @param viewModel ViewModel for current screen
 */
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
internal fun DetailMangaScreen(
    navigator: Navigator,
    viewModel: DetailMangaViewModel,
) {
    val result = viewModel.screenState.collectAsState().value
    when (result) {
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
                    onChapterClick = { chapterId ->
                        viewModel.saveChapters(result.data.chapters.map { it.id.orEmpty() })
                        navigator.navigate("${MangaChapterScreen.ROUTE}/${result.data.id}/${chapterId}")
                    },
                    onChangeStatus = { viewModel.showMangaStatusBottomSheet() }
                )
            }
        }
    }

    when (viewModel.bottomSheet.collectAsState().value) {
        true -> {
            MangaStatusBottomSheetView(
                (result as? DetailScreenState.Success)?.data?.mangaStatus ?: MangaReadStatus.UNKNOWN,
                onChangeStatus = { viewModel.saveUpdatedInfo(it) },
                onDismiss = { viewModel.hideMangaStatusBottomSheet() }
            )
        }
        false -> Unit
    }
}