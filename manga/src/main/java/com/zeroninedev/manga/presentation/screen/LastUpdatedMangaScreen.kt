package com.zeroninedev.manga.presentation.screen

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.zeroninedev.core_compose.components.image.MangaPreviewImageWithTitle
import com.zeroninedev.manga.presentation.viewmodel.LastUpdatedMangaViewModel
import com.zeroninedev.navigation.actions.Navigator
import com.zeroninedev.navigation.destination.Screen

@Composable
internal fun LastUpdatedMangaScreen(
    internalNavigation: Navigator,
    mainNavigation: Navigator,
    viewModel: LastUpdatedMangaViewModel
) {
    val screen = viewModel.screenState.collectAsState().value
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(items = screen) { item ->
            MangaPreviewImageWithTitle(title = item.title, imageUrl = item.imageUrl, imageDescription = item.title) {
                internalNavigation.navigate("${Screen.MangaDetailScreen.ROUTE}/${item.id}")
            }
        }
    }
}