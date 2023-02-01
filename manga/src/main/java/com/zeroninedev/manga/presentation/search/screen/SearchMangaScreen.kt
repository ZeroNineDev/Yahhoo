package com.zeroninedev.manga.presentation.search.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.zeroninedev.manga.presentation.search.view.SearchMangaView
import com.zeroninedev.manga.presentation.search.view.SearchMangaViewList
import com.zeroninedev.manga.presentation.search.viewmodel.SearchMangaViewModel
import com.zeroninedev.navigation.actions.Navigator
import com.zeroninedev.navigation.destination.Screen.MangaDetailScreen

@Composable
internal fun SearchMangaScreen(mainNavigation: Navigator, viewModel: SearchMangaViewModel) {
    val screen = viewModel.screenState.collectAsState().value

    Column() {
        SearchMangaView(
            onQueryTextChange = { viewModel.launchSearch(it) },
            onClearQueryClick = { viewModel.clearQuery() }
        )
        SearchMangaViewList(items = screen) { item ->
            mainNavigation.navigate("${MangaDetailScreen.ROUTE}/${item.id}")
        }
    }
}