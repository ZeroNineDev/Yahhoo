package com.zeroninedev.manga.presentation.popular.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.paging.compose.collectAsLazyPagingItems
import com.zeroninedev.manga.presentation.popular.view.PopularMangaView
import com.zeroninedev.manga.presentation.popular.viewmodel.PopularMangaViewModel
import com.zeroninedev.navigation.actions.Navigator
import com.zeroninedev.navigation.destination.Screen.MangaDetailScreen

@ExperimentalComposeApi
@Composable
internal fun PopularMangaScreen(
    mainNavigation: Navigator,
    viewModel: PopularMangaViewModel
) {
    val screen = viewModel.screenState.collectAsLazyPagingItems()
    PopularMangaView(items = screen) { item ->
        mainNavigation.navigate("${MangaDetailScreen.ROUTE}/${item.id}")
    }
}