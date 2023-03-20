package com.zeroninedev.manga.presentation.search.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.zeroninedev.core_compose.components.layout.SearchLayout
import com.zeroninedev.core_compose.components.screen.ErrorScreen
import com.zeroninedev.core_compose.components.screen.LoadingScreen
import com.zeroninedev.manga.presentation.search.view.SearchMangaViewList
import com.zeroninedev.manga.presentation.search.viewmodel.SearchMangaIntent
import com.zeroninedev.manga.presentation.search.viewmodel.SearchMangaViewModel
import com.zeroninedev.navigation.actions.Navigator
import com.zeroninedev.navigation.destination.Screen.MangaDetailScreen

/**
 * Search manga screen
 *
 * @param mainNavigation main navigator
 * @param viewModel ViewModel for current screen
 */
@Composable
internal fun SearchMangaScreen(
    mainNavigation: Navigator,
    viewModel: SearchMangaViewModel
) {
    SearchLayout(
        onClearQuery = { viewModel.processIntent(SearchMangaIntent.ClearResponse) },
        onTextChange = { viewModel.processIntent(SearchMangaIntent.Typing(it)) }
    ) {
        when (val result = viewModel.screenState.collectAsState().value) {
            is SearchScreenState.Loading -> {
                LoadingScreen()
            }
            is SearchScreenState.Empty -> {
                Text(text = "Пусто")
            }

            is SearchScreenState.Error -> {
                ErrorScreen(errorMessage = result.exception) {
                    viewModel.processIntent(SearchMangaIntent.ClearResponse)
                }
            }

            is SearchScreenState.Success -> {
                SearchMangaViewList(items = result.data) { item ->
                    mainNavigation.navigate(MangaDetailScreen.getRoute(item.id))
                }
            }
        }
    }
}