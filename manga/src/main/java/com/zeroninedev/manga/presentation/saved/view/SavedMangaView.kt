package com.zeroninedev.manga.presentation.saved.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.zeroninedev.common.domain.models.MangaReadStatus
import com.zeroninedev.common.domain.models.UpdatedManga
import com.zeroninedev.core_compose.components.chip.CategorySimpleChip
import com.zeroninedev.core_compose.components.image.MangaPreviewImageWithTitle
import com.zeroninedev.core_compose.components.layout.RowWithWrap
import com.zeroninedev.core_compose.components.layout.gridItems
import com.zeroninedev.core_compose.model.toBackgroundColor
import com.zeroninedev.core_compose.model.toMangaPresentationString
import com.zeroninedev.core_compose.ui.theme.MediumSize
import com.zeroninedev.core_compose.ui.theme.TinySize
import com.zeroninedev.manga.domain.model.allSavedMangaStatuses
import com.zeroninedev.manga.domain.model.toUiStatus

/**
 * Saved manga view
 *
 * @param items list of mangas
 * @param onMangaClick callback on manga click
 */
@Composable
internal fun SavedMangaView(
    items: List<UpdatedManga>,
    currentFilter: MangaReadStatus,
    onMangaClick: (UpdatedManga) -> Unit,
    onChangeStatus: (MangaReadStatus) -> Unit
) {
    LazyColumn {
        item {
            RowWithWrap(
                modifier = Modifier.padding(horizontal = MediumSize),
                verticalSpacer = TinySize,
                horizontalSpacer = TinySize
            ) {
                allSavedMangaStatuses().forEach {
                    val status = it.toUiStatus()
                    CategorySimpleChip(
                        text = status.toMangaPresentationString(),
                        backgroundText = if (currentFilter == it) status.toBackgroundColor() else Color.Transparent,
                        onChipClick = { onChangeStatus(it) }
                    )
                }
            }
        }
        gridItems(
            items = items,
            columnCount = 2
        ) { item ->
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