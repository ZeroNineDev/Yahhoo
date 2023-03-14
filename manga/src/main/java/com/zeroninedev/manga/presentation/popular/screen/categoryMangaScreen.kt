package com.zeroninedev.manga.presentation.popular.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.collectAsState
import androidx.paging.compose.collectAsLazyPagingItems
import com.zeroninedev.core_compose.components.screen.ErrorScreen
import com.zeroninedev.core_compose.components.screen.LoadingScreen
import com.zeroninedev.manga.presentation.popular.view.PopularMangaView
import com.zeroninedev.manga.presentation.popular.viewmodel.PopularMangaViewModel
import com.zeroninedev.navigation.actions.Navigator
import com.zeroninedev.navigation.destination.Screen.MangaDetailScreen

/**
 * Popular manga screen
 *
 * @param mainNavigation main navigator
 * @param viewModel ViewModel for current screen
 */
@ExperimentalComposeApi
@Composable
internal fun PopularMangaScreen(
    mainNavigation: Navigator,
    viewModel: PopularMangaViewModel
) {
    when (val result = viewModel.screenState.collectAsState().value) {
        is PopularScreenState.Error -> {
            ErrorScreen(errorMessage = result.exception) { viewModel.updateRequest() }
        }
        is PopularScreenState.Loading -> {
            LoadingScreen()
        }
        is PopularScreenState.Success -> {
            val screen = result.data.collectAsLazyPagingItems()
            PopularMangaView(items = screen) { item ->
                mainNavigation.navigate("${MangaDetailScreen.ROUTE}/${item.id}")
            }
        }
    }
}