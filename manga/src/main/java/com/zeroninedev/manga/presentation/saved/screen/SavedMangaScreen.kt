package com.zeroninedev.manga.presentation.saved.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import com.zeroninedev.core_compose.components.screen.ErrorScreen
import com.zeroninedev.core_compose.components.screen.LoadingScreen
import com.zeroninedev.manga.presentation.saved.view.SavedMangaView
import com.zeroninedev.manga.presentation.saved.viewmodel.SavedMangaViewModel
import com.zeroninedev.navigation.actions.Navigator
import com.zeroninedev.navigation.destination.Screen.MangaDetailScreen

@Composable
internal fun SavedMangaScreen(
    mainNavigation: Navigator,
    viewModel: SavedMangaViewModel
) {
    LaunchedEffect(key1 = null) {
        viewModel.updateRequest()
    }

    when (val result = viewModel.screenState.collectAsState().value) {
        is SavedScreenState.Error -> {
            ErrorScreen(errorMessage = result.exception) { viewModel.updateRequest() }
        }
        is SavedScreenState.Loading -> {
            LoadingScreen()
        }
        is SavedScreenState.Success -> {
            SavedMangaView(items = result.data) { item ->
                mainNavigation.navigate("${MangaDetailScreen.ROUTE}/${item.id}")
            }
        }
        is SavedScreenState.Empty -> {
            ErrorScreen(
                errorMessage = stringResource(id = com.zeroninedev.core_compose.R.string.saved_mangas_empty)
            ) {
                viewModel.updateRequest()
            }
        }
    }
}