package com.zeroninedev.manga.presentation.search.view

import androidx.compose.foundation.lazy.grid.GridCells.Fixed
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import com.zeroninedev.common.domain.models.UpdatedManga
import com.zeroninedev.core_compose.components.image.MangaPreviewImageWithTitle
import com.zeroninedev.core_compose.components.input.SearchInputText

@Composable
internal fun SearchMangaView(
    onQueryTextChange: (String) -> Unit,
    onClearQueryClick: () -> Unit
) {
    SearchInputText(
        showClearIcon = true,
        onQueryTextChange = onQueryTextChange,
        onClearClick = onClearQueryClick
    )
}

@Composable
internal fun SearchMangaViewList(
    items: List<UpdatedManga>,
    onMangaClick: (UpdatedManga) -> Unit,
) {
    LazyVerticalGrid(columns = Fixed(2)) {
        items(items = items) { item ->
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