package com.zeroninedev.core_compose.components.image

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest.Builder
import coil.request.ImageRequest.Listener
import coil.size.Size

@Composable
internal fun BaseImageLoader(
    url: String,
    modifier: Modifier = Modifier,
    listener: Listener? = null,
) {
    val painter = rememberAsyncImagePainter(
        model = Builder(LocalContext.current)
            .data(url.drop(1).dropLast(1))
            .listener(listener)
            .allowHardware(false)
            .size(Size.ORIGINAL)
            .build()
    )

    Image(
        contentDescription = null,
        painter = painter,
        modifier = modifier,
        contentScale = ContentScale.FillWidth,
    )
}