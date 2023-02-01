package com.zeroninedev.manga.presentation.lastupdated.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.zeroninedev.manga.presentation.lastupdated.view.LastUpdatedMangaView
import com.zeroninedev.manga.presentation.lastupdated.viewmodel.LastUpdatedMangaViewModel
import com.zeroninedev.navigation.actions.Navigator
import com.zeroninedev.navigation.destination.Screen.MangaDetailScreen

@Composable
internal fun LastUpdatedMangaScreen(
    mainNavigation: Navigator,
    viewModel: LastUpdatedMangaViewModel
) {
    val screen = viewModel.screenState.collectAsState().value
    LastUpdatedMangaView(items = screen) { item ->
        mainNavigation.navigate("${MangaDetailScreen.ROUTE}/${item.id}")
    }
}