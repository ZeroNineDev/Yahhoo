package com.zeroninedev.core_compose.components.text

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.zeroninedev.core_compose.ui.theme.MediumSize
import com.zeroninedev.core_compose.ui.theme.OneSize
import com.zeroninedev.core_compose.ui.theme.SmallSize
import com.zeroninedev.core_compose.ui.theme.TinySize
import com.zeroninedev.core_compose.ui.theme.YahhooShapes
import com.zeroninedev.core_compose.ui.theme.yahhooTypography

/**
 * Title with border corner and background for represent manga chapter
 *
 * @param modifier entered modifier from other scope
 * @param isWatched state manga already watched
 * @param chapterTitle title of manga
 * @param onChapterClick callback on manga chapter click
 */
@ExperimentalFoundationApi
@Composable
fun MangaChapterTitle(
    modifier: Modifier = Modifier,
    isWatched: Boolean = false,
    chapterTitle: String,
    onChapterLongClick: () -> Unit,
    onChapterClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = TinySize, horizontal = MediumSize)
            .combinedClickable(
                onClick = onChapterClick,
                onLongClick = onChapterLongClick,
            )
            .clip(YahhooShapes.small)
            .border(
                border = BorderStroke(
                    width = OneSize,
                    color = MaterialTheme.colors.primaryVariant
                )
            )
            .background(if (isWatched) Color.Transparent else MaterialTheme.colors.primary),
    ) {
        Text(
            text = chapterTitle,
            style = yahhooTypography.body1,
            color = MaterialTheme.colors.primaryVariant,
            modifier = Modifier.padding(SmallSize)
        )
    }
}