package com.zeroninedev.manga.presentation.popular.view

import androidx.compose.foundation.lazy.grid.GridCells.Fixed
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.paging.compose.LazyPagingItems
import com.zeroninedev.common.domain.models.UpdatedManga
import com.zeroninedev.core_compose.components.image.MangaPreviewImageWithTitle
import com.zeroninedev.core_compose.components.layout.items

@Composable
@ExperimentalComposeApi
internal fun PopularMangaView(
    items: LazyPagingItems<UpdatedManga>,
    onMangaClick: (UpdatedManga) -> Unit,
) {
    LazyVerticalGrid(columns = Fixed(2)) {
        items(items = items) { item ->
            if (item != null) {
                MangaPreviewImageWithTitle(
                    title = item.title,
                    imageUrl = item.imageUrl,
                    imageDescription = item.title
                ) {
                    onMangaClick(item)
                }
            }
        }
    }
}