package com.zeroninedev.core_compose.components.chip

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.zeroninedev.core_compose.ui.theme.OneSize
import com.zeroninedev.core_compose.ui.theme.SmallSize

/**
 * Chip with rounded bordered corner,
 * transparent background and text
 *
 * @param modifier entered modifier from other scope
 * @param text text in chip
 */
@Composable
fun CategorySimpleChip(
    text: String,
    modifier: Modifier = Modifier,
    backgroundText: Color = Color.Transparent,
    onChipClick: (() -> Unit)? = null
) {
    Surface(
        color = Color.Transparent,
        contentColor = MaterialTheme.colors.primaryVariant,
        shape = CircleShape,
        border = BorderStroke(
            width = OneSize,
            color = MaterialTheme.colors.primaryVariant
        ),
        modifier = modifier
            .clip(CircleShape)
            .background(backgroundText)
            .clickable { onChipClick?.invoke() }
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.primaryVariant,
            modifier = Modifier.padding(SmallSize)
        )
    }
}