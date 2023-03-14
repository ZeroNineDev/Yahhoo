package com.zeroninedev.manga.presentation.detail.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zeroninedev.common.domain.models.Manga
import com.zeroninedev.core_compose.components.button.RoundedIconButton
import com.zeroninedev.core_compose.components.chip.CategorySimpleChip
import com.zeroninedev.core_compose.components.image.BackgroundImageView
import com.zeroninedev.core_compose.components.layout.RowWithWrap
import com.zeroninedev.core_compose.components.text.DescriptionText
import com.zeroninedev.core_compose.components.text.ExpandableTextView
import com.zeroninedev.core_compose.components.text.GenreWithDescriptionText
import com.zeroninedev.core_compose.components.text.MangaChapterTitle
import com.zeroninedev.core_compose.components.text.SubTitleText
import com.zeroninedev.core_compose.model.toBackgroundColor
import com.zeroninedev.core_compose.model.toColor
import com.zeroninedev.core_compose.model.toDrawable
import com.zeroninedev.core_compose.ui.theme.BigSize
import com.zeroninedev.core_compose.ui.theme.ExtraSize
import com.zeroninedev.core_compose.ui.theme.MediumSize
import com.zeroninedev.core_compose.ui.theme.TinySize
import com.zeroninedev.core_compose.ui.theme.TransparentColor
import com.zeroninedev.core_compose.ui.theme.YahhooShapes
import com.zeroninedev.manga.R.string
import com.zeroninedev.manga.domain.model.toUiStatus

/**
 * Detail manga view
 *
 * @param manga manga info
 * @param onChapterClick callback on chapter click
 */
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
internal fun DetailMangaView(
    manga: Manga,
    onChapterLongClick: (String) -> Unit,
    onChapterClick: (String) -> Unit,
    onChangeStatus: () -> Unit
) {
    Box { manga.image?.let { BackgroundImageView(modifier = Modifier.fillMaxWidth(), imageUrl = it) } }
    Row(
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
        RoundedIconButton(
            icon = manga.mangaStatus.toUiStatus().toDrawable(),
            tint = manga.mangaStatus.toUiStatus().toColor(),
            backgroundColor = manga.mangaStatus.toUiStatus().toBackgroundColor(),
            onClick = onChangeStatus
        )
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
        item {
            manga.status?.let {
                GenreWithDescriptionText(
                    genre = stringResource(string.status_genre_text),
                    description = it
                )
            }
        }
        item {
            manga.genre?.let {
                GenreWithDescriptionText(
                    genre = stringResource(string.genre_text),
                    description = it
                )
            }
        }
        item {
            manga.anotherTitle?.let {
                GenreWithDescriptionText(
                    genre = stringResource(string.another_names_text),
                    description = it
                )
            }
        }
        item {
            manga.author?.let {
                GenreWithDescriptionText(
                    genre = stringResource(string.author_text),
                    description = it
                )
            }
        }
        item {
            manga.drawer?.let {
                GenreWithDescriptionText(
                    genre = stringResource(string.drawer_text),
                    description = it
                )
            }
        }
        item {
            manga.views?.let {
                GenreWithDescriptionText(
                    genre = stringResource(string.views_text),
                    description = it
                )
            }
        }
        item {
            manga.translator?.let {
                GenreWithDescriptionText(
                    genre = stringResource(string.translator_text),
                    description = it
                )
            }
        }

        item { SubTitleText(text = stringResource(string.category_subtitle)) }

        item {
            RowWithWrap(
                modifier = Modifier.padding(horizontal = MediumSize),
                verticalSpacer = TinySize,
                horizontalSpacer = TinySize
            ) {
                manga.category.forEach { category -> category.name?.let { CategorySimpleChip(text = it) } }
            }
        }

        item { SubTitleText(text = stringResource(string.description_subtitle)) }

        item { manga.description?.let { DescriptionText(text = it) } }

        item {
            SubTitleText(
                text = stringResource(
                    if (manga.chapters.isEmpty()) string.empty_chapters_text else
                        string.chapters_text
                )
            )
        }
        items(manga.chapters) {
            MangaChapterTitle(
                isWatched = it.wasRead,
                chapterTitle = it.title.orEmpty(),
                onChapterClick = { onChapterClick(it.id.orEmpty()) },
                onChapterLongClick = { onChapterLongClick(it.id.orEmpty()) }
            )
        }

        item { Spacer(modifier = Modifier.height(80.dp)) }
    }
}