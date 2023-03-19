package com.zeroninedev.core_compose.components.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TransparentButton(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit
) {
    Spacer(
        modifier = modifier
            .width(70.dp)
            .fillMaxHeight()
            .clickable { onButtonClick() }
    )
}