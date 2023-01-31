package com.zeroninedev.manga.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.zeroninedev.manga.presentation.view.LastUpdatedMangaView
import com.zeroninedev.manga.presentation.viewmodel.LastUpdatedMangaViewModel
import com.zeroninedev.navigation.actions.Navigator
import com.zeroninedev.navigation.destination.Screen

@Composable
internal fun LastUpdatedMangaScreen(
    mainNavigation: Navigator,
    viewModel: LastUpdatedMangaViewModel
) {
    val screen = viewModel.screenState.collectAsState().value
    LastUpdatedMangaView(items = screen) { item ->
        mainNavigation.navigate("${Screen.MangaDetailScreen.ROUTE}/${item.id}")
    }
}