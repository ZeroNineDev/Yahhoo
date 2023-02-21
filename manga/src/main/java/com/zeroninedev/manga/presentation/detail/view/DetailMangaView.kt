package com.zeroninedev.manga.presentation.detail.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.zeroninedev.common.domain.models.Manga
import com.zeroninedev.core_compose.components.chip.CategorySimpleChip
import com.zeroninedev.core_compose.components.image.BackgroundImageView
import com.zeroninedev.core_compose.components.layout.RowWithWrap
import com.zeroninedev.core_compose.components.text.DescriptionText
import com.zeroninedev.core_compose.components.text.ExpandableTextView
import com.zeroninedev.core_compose.components.text.GenreWithDescriptionText
import com.zeroninedev.core_compose.components.text.MangaChapterTitle
import com.zeroninedev.core_compose.components.text.SubTitleText
import com.zeroninedev.core_compose.ui.theme.BigSize
import com.zeroninedev.core_compose.ui.theme.ExtraSize
import com.zeroninedev.core_compose.ui.theme.MediumSize
import com.zeroninedev.core_compose.ui.theme.TinySize
import com.zeroninedev.core_compose.ui.theme.TransparentColor
import com.zeroninedev.core_compose.ui.theme.YahhooShapes

@ExperimentalAnimationApi
@Composable
internal fun DetailMangaView(
    manga: Manga,
    onChapterClick: (String) -> Unit
) {
    Box { manga.image?.let { BackgroundImageView(modifier = Modifier.fillMaxWidth(), imageUrl = it) } }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = TransparentColor)
            .padding(top = BigSize, start = BigSize)
            .clip(YahhooShapes.medium)
    ) {
        manga.image?.let {
            BackgroundImageView(
                modifier = Modifier
                    .size(width = 200.dp, height = ExtraSize)
                    .clip(YahhooShapes.medium),
                imageUrl = it
            )
        }
    }

    LazyColumn(
        modifier = Modifier
            .padding(top = 220.dp)
            .clip(RoundedCornerShape(topStart = BigSize, topEnd = BigSize))
            .fillMaxHeight()
            .background(MaterialTheme.colors.background)
    ) {
        item { Spacer(modifier = Modifier.height(BigSize)) }
        item { manga.title?.let { ExpandableTextView(text = it) } }
        item { manga.status?.let { GenreWithDescriptionText(genre = "Статус", description = it) } }
        item { manga.genre?.let { GenreWithDescriptionText(genre = "Жанр", description = it) } }
        item { manga.anotherTitle?.let { GenreWithDescriptionText(genre = "Другие названия", description = it) } }
        item { manga.author?.let { GenreWithDescriptionText(genre = "Автор", description = it) } }
        item { manga.drawer?.let { GenreWithDescriptionText(genre = "Художник", description = it) } }
        item { manga.views?.let { GenreWithDescriptionText(genre = "Просмотры", description = it) } }
        item { manga.translator?.let { GenreWithDescriptionText(genre = "Переводчик", description = it) } }

        item { SubTitleText(text = "Категории:") }

        item {
            RowWithWrap(
                modifier = Modifier.padding(horizontal = MediumSize),
                verticalSpacer = TinySize,
                horizontalSpacer = TinySize
            ) {
                manga.category.forEach { category -> category.name?.let { CategorySimpleChip(it) } }
            }
        }

        item { SubTitleText(text = "Описание:") }

        item { manga.description?.let { DescriptionText(it) } }

        item { SubTitleText(text = if (manga.chapters.isEmpty()) "Глав нет(" else "Главы:") }
        items(manga.chapters) { MangaChapterTitle(chapterTitle = it.title.orEmpty()) { onChapterClick(it.id.orEmpty()) } }

        item { Spacer(modifier = Modifier.height(80.dp)) }
    }
}