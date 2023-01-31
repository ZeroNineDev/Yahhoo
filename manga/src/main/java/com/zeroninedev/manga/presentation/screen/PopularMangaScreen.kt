package com.zeroninedev.manga.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.zeroninedev.manga.presentation.view.PopularMangaView
import com.zeroninedev.manga.presentation.viewmodel.PopularMangaViewModel
import com.zeroninedev.navigation.actions.Navigator
import com.zeroninedev.navigation.destination.Screen

@Composable
internal fun PopularMangaScreen(
    mainNavigation: Navigator,
    viewModel: PopularMangaViewModel
) {
    val screen = viewModel.screenState.collectAsState().value
    PopularMangaView(items = screen) { item ->
        mainNavigation.navigate("${Screen.MangaDetailScreen.ROUTE}/${item.id}")
    }
}