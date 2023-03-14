package com.zeroninedev.core_compose.components.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.zeroninedev.core_compose.ui.theme.MediumSize
import com.zeroninedev.core_compose.ui.theme.TinySize
import com.zeroninedev.core_compose.ui.theme.yahhooTypography

/**
 * Text view to represent chapter page
 *
 * @param modifier entered modifier from other scope
 * @param text chapter page text
 */
@Composable
fun ChapterPagesTextView(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        text = text,
        style = yahhooTypography.body1,
        color = MaterialTheme.colors.primaryVariant,
        textAlign = TextAlign.Center,
        modifier = modifier.fillMaxWidth().padding(horizontal = MediumSize, vertical = TinySize)
    )
}