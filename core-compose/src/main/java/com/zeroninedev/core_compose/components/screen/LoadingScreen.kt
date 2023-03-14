package com.zeroninedev.core_compose.components.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.zeroninedev.core_compose.components.image.GifImageView

/**
 * Simple loading screen for first version
 * which show progress indicator
 *
 * @param modifier entered modifier from other scope
 */
@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        GifImageView()
    }
}