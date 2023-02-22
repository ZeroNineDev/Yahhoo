package com.zeroninedev.core_compose.components.image

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest.Builder
import coil.size.Size

/**
 * View to show background image
 *
 * @param modifier entered modifier from other scope
 * @param imageUrl image url
 */
@Composable
fun BackgroundImageView(
    modifier: Modifier = Modifier,
    imageUrl: String
) {
    val painter = rememberAsyncImagePainter(
        model = Builder(LocalContext.current)
            .data(imageUrl)
            .allowHardware(false)
            .size(Size.ORIGINAL)
            .crossfade(true)
            .build()
    )

    Image(
        modifier = modifier,
        contentScale = ContentScale.Crop,
        painter = painter,
        contentDescription = imageUrl
    )
}