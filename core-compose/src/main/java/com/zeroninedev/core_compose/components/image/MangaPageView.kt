package com.zeroninedev.core_compose.components.image

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.ImageRequest.Listener
import coil.request.SuccessResult
import coil.size.Size

/**
 * View to show manga page
 *
 * @param url manga page url
 * @param onErrorResult message when image loaded with error
 * @param onSuccessResult return drawable
 */
@Composable
fun MangaPageView(
    modifier: Modifier = Modifier,
    url: String,
    onErrorResult: (String?) -> Unit,
    onSuccessResult: (Drawable) -> Unit
) {
    val scrollableState = rememberScrollState()

    var listener by remember {
        mutableStateOf<Listener?>(
            object : Listener {

                override fun onError(request: ImageRequest, result: ErrorResult) {
                    super.onError(request, result)
                    onErrorResult(result.throwable.message)
                }

                override fun onSuccess(request: ImageRequest, result: SuccessResult) {
                    super.onSuccess(request, result)
                    onSuccessResult(result.drawable)
                }
            }
        )
    }


    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url.drop(1).dropLast(1))
            .listener(listener)
            .allowHardware(true)
            .size(Size.ORIGINAL)
            .build()
    )

    var scale by remember { mutableStateOf(1f) }
    var translationX by remember { mutableStateOf(0f) }
    var translationY by remember { mutableStateOf(0f) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    listener = null
                    scale *= zoom
                    translationY += pan.y
                    translationX += pan.x
                }
            }
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        scale = if (scale == 1f) 3f else 1f
                        translationX = if (translationX == 0f) -(it.x - size.width / 3f) else 0f
                        translationY = if (translationY == 0f) -(it.y - size.height / 3f) else 0f
                    }
                )
            }
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale,
                translationX = translationX,
                translationY = translationY,
            )
            .wrapContentSize(align = Alignment.Center),
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollableState),
            contentScale = ContentScale.FillWidth,
            painter = painter,
            contentDescription = url,
        )
    }
}