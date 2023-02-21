package com.zeroninedev.manga.presentation.search.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.zeroninedev.core_compose.components.screen.ErrorScreen
import com.zeroninedev.core_compose.components.screen.LoadingScreen
import com.zeroninedev.manga.presentation.search.view.SearchMangaView
import com.zeroninedev.manga.presentation.search.view.SearchMangaViewList
import com.zeroninedev.manga.presentation.search.viewmodel.SearchMangaViewModel
import com.zeroninedev.navigation.actions.Navigator
import com.zeroninedev.navigation.destination.Screen.MangaDetailScreen

@Composable
internal fun SearchMangaScreen(
    mainNavigation: Navigator,
    viewModel: SearchMangaViewModel
) {
    Column() {
        SearchMangaView(
            onQueryTextChange = { viewModel.launchSearch(it) },
            onClearQueryClick = { viewModel.clearQuery() }
        )

        when (val result = viewModel.screenState.collectAsState().value) {
            is SearchScreenState.Error -> {
                ErrorScreen(result.exception) { viewModel.relaunch() }
            }
            is SearchScreenState.Loading -> {
                LoadingScreen()
            }
            is SearchScreenState.Empty -> {}
            is SearchScreenState.Success -> {
                SearchMangaViewList(items = result.data) { item ->
                    mainNavigation.navigate("${MangaDetailScreen.ROUTE}/${item.id}")
                }
            }
        }
    }
}