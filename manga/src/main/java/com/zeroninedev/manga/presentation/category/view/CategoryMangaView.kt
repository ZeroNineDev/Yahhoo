package com.zeroninedev.manga.presentation.category.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.grid.GridCells.Fixed
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.zeroninedev.common.domain.models.UpdatedManga
import com.zeroninedev.core_compose.components.image.MangaPreviewImageWithTitle
import com.zeroninedev.core_compose.components.layout.items
import com.zeroninedev.core_compose.components.text.ScreenTitleTextView

/**
 * Popular mangas view
 *
 * @param items mangas list
 * @param title
 * @param onMangaClick callback on manga click
 */
@Composable
@ExperimentalComposeApi
internal fun CategoryMangaView(
    items: LazyPagingItems<UpdatedManga>,
    title: String,
    onMangaClick: (UpdatedManga) -> Unit,
) {
    Column {
        ScreenTitleTextView(
            text = title, modifier = Modifier
                .fillMaxWidth()
                .padding(
                    WindowInsets.systemBars
                        .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top)
                        .asPaddingValues()
                )
        )

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
}