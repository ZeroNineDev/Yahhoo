package com.zeroninedev.core_compose.components.text

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zeroninedev.core_compose.ui.theme.MediumSize
import com.zeroninedev.core_compose.ui.theme.SmallSize
import com.zeroninedev.core_compose.ui.theme.TinySize
import com.zeroninedev.core_compose.ui.theme.yahhooTypography

/**
 * Text View for Genre and Description
 *
 * @param modifier entered modifier from other scope
 * @param genre genre
 * @param description description
 */
@Composable
fun GenreWithDescriptionText(
    modifier: Modifier = Modifier,
    genre: String,
    description: String
) {
    Row(modifier = modifier.fillMaxWidth().padding(vertical = TinySize)) {
        Text(
            text = genre.plus(ADDITIONAL_SYMBOL),
            style = yahhooTypography.body2,
            color = MaterialTheme.colors.primaryVariant,
            modifier = Modifier.padding(start = MediumSize)
        )
        Text(
            text = description,
            style = yahhooTypography.body1,
            color = MaterialTheme.colors.primaryVariant,
            modifier = Modifier.padding(start = SmallSize)
        )
    }
}

private const val ADDITIONAL_SYMBOL = ":"