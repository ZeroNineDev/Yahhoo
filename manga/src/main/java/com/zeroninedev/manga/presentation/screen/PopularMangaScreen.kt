package com.zeroninedev.manga.presentation.screen

import androidx.compose.foundation.lazy.grid.GridCells.Fixed
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.zeroninedev.core_compose.components.image.MangaPreviewImageWithTitle
import com.zeroninedev.manga.presentation.viewmodel.PopularMangaViewModel
import com.zeroninedev.navigation.actions.Navigator

@Composable
internal fun PopularMangaScreen(
    internalNavigation: Navigator,
    mainNavigation: Navigator,
    viewModel: PopularMangaViewModel
) {
    val screen = viewModel.screenState.collectAsState().value
    LazyVerticalGrid(columns = Fixed(2)) {
        items(items = screen) { item ->
            MangaPreviewImageWithTitle(title = item.title, imageUrl = item.imageUrl, imageDescription = item.title) {

            }
        }
    }
}