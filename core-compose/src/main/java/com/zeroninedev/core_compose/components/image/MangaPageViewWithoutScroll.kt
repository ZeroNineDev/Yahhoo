package com.zeroninedev.core_compose.components.image

import android.graphics.drawable.Drawable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.request.ImageRequest.Listener

@Composable
fun MangaPageViewWithoutScroll(
    url: String,
    onErrorResult: (String?) -> Unit,
    onSuccessResult: (Drawable) -> Unit,
    modifier: Modifier = Modifier,
) {
    val listener by remember {
        mutableStateOf<Listener?>(
            ImageListener(
                onErrorResult = onErrorResult,
                onSuccessResult = onSuccessResult
            )
        )
    }

    BaseImageLoader(
        url = url,
        listener = listener,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    )
}

@Preview
@Composable
fun TestManga() {
    MangaPageViewWithoutScroll(
        url = " https://mangabook.org/uploads/manga/i-yongsa-silhwanya/chapters/v1-ch1/001.jpg ",
        onErrorResult = {},
        onSuccessResult = {}
    )
}