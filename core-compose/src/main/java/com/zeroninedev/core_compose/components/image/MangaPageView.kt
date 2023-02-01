package com.zeroninedev.core_compose.components.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size

/**
 * View to show manga page
 *
 * @param url manga page url
 */
@Composable
fun MangaPageView(url: String) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url.drop(1).dropLast(1))
            .allowHardware(true)
            .size(Size.ORIGINAL)
            .build()
    )

    Image(
        modifier = Modifier
            .fillMaxSize(),
        contentScale = ContentScale.FillWidth,
        painter = painter,
        contentDescription = url
    )
}