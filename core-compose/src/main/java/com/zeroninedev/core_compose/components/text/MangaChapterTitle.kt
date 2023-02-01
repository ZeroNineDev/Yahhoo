package com.zeroninedev.core_compose.components.text

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.zeroninedev.core_compose.ui.theme.MediumSize
import com.zeroninedev.core_compose.ui.theme.SmallSize
import com.zeroninedev.core_compose.ui.theme.TinySize
import com.zeroninedev.core_compose.ui.theme.YahhooShapes
import com.zeroninedev.core_compose.ui.theme.yahhooTypography

@Composable
fun MangaChapterTitle(
    modifier: Modifier = Modifier,
    chapterTitle: String,
    onChapterClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = TinySize, horizontal = MediumSize)
            .clickable { onChapterClick() }
            .clip(YahhooShapes.small)
            .background(MaterialTheme.colors.primary)
    ) {
        Text(
            text = chapterTitle,
            style = yahhooTypography.body1,
            color = MaterialTheme.colors.primaryVariant,
            modifier = Modifier.padding(SmallSize)
        )
    }
}