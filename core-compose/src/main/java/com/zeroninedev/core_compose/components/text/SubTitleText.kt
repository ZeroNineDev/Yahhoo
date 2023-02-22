package com.zeroninedev.core_compose.components.text

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zeroninedev.core_compose.ui.theme.MediumSize
import com.zeroninedev.core_compose.ui.theme.SmallSize
import com.zeroninedev.core_compose.ui.theme.yahhooTypography

/**
 * Text View for sub title of screen
 *
 * @param modifier entered modifier from other scope
 * @param text text to show
 */
@Composable
fun SubTitleText(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        text = text,
        style = yahhooTypography.h6,
        color = MaterialTheme.colors.primaryVariant,
        modifier = modifier.padding(start = MediumSize, end = MediumSize, top = MediumSize, bottom = SmallSize)
    )
}