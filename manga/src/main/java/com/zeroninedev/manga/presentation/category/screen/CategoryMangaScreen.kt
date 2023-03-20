package com.zeroninedev.manga.presentation.category.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.collectAsState
import androidx.paging.compose.collectAsLazyPagingItems
import com.zeroninedev.core_compose.components.screen.ErrorScreen
import com.zeroninedev.core_compose.components.screen.LoadingScreen
import com.zeroninedev.manga.presentation.category.screen.CategoryScreenState.Error
import com.zeroninedev.manga.presentation.category.screen.CategoryScreenState.Loading
import com.zeroninedev.manga.presentation.category.screen.CategoryScreenState.Success
import com.zeroninedev.manga.presentation.category.view.CategoryMangaView
import com.zeroninedev.manga.presentation.category.viewmodel.CategoryMangaIntent
import com.zeroninedev.manga.presentation.category.viewmodel.CategoryMangaViewModel
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
internal fun CategoryMangaScreen(
    mainNavigation: Navigator,
    viewModel: CategoryMangaViewModel
) {
    when (val result = viewModel.screenState.collectAsState().value) {
        is Error -> {
            ErrorScreen(errorMessage = result.exception) { viewModel.processIntent(CategoryMangaIntent.UpdateResponse) }
        }
        is Loading -> {
            LoadingScreen()
        }
        is Success -> {
            val screen = result.data.collectAsLazyPagingItems()
            CategoryMangaView(items = screen, title = result.name) { item ->
                mainNavigation.navigate(MangaDetailScreen.getRoute(item.id))
            }
        }
    }
}