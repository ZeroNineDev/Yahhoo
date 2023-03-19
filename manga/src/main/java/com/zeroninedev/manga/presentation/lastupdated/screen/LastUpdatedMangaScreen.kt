package com.zeroninedev.manga.presentation.lastupdated.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.zeroninedev.core_compose.components.screen.ErrorScreen
import com.zeroninedev.core_compose.components.screen.LoadingScreen
import com.zeroninedev.manga.presentation.lastupdated.view.LastUpdatedMangaView
import com.zeroninedev.manga.presentation.lastupdated.viewmodel.LastUpdatedMangaViewModel
import com.zeroninedev.navigation.actions.Navigator
import com.zeroninedev.navigation.destination.Screen.MangaDetailScreen


/**
 * Detail manga screen
 *
 * @param mainNavigation main navigator
 * @param viewModel ViewModel for current screen
 */
@Composable
internal fun LastUpdatedMangaScreen(
    mainNavigation: Navigator,
    viewModel: LastUpdatedMangaViewModel
) {
    when (val result = viewModel.screenState.collectAsState().value) {
        is LastUpdatedScreenState.Error -> {
            ErrorScreen(errorMessage = result.exception) { viewModel.updateRequest() }
        }
        is LastUpdatedScreenState.Loading -> {
            LoadingScreen()
        }
        is LastUpdatedScreenState.Success -> {
            LastUpdatedMangaView(items = result.data) { item ->
                mainNavigation.navigate(MangaDetailScreen.getRoute(item.id))
            }
        }
    }
}