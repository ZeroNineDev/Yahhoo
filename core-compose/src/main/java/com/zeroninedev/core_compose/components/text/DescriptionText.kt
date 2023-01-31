package com.zeroninedev.core_compose.components.text

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zeroninedev.core_compose.ui.theme.MediumSize
import com.zeroninedev.core_compose.ui.theme.TinySize
import com.zeroninedev.core_compose.ui.theme.yahhooTypography

@Composable
fun DescriptionText(
    text: String
) {
    Text(
        text = text,
        style = yahhooTypography.body1,
        color = MaterialTheme.colors.primaryVariant,
        modifier = Modifier.padding(horizontal = MediumSize, vertical = TinySize)
    )
}