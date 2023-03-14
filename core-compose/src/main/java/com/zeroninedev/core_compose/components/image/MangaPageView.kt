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
 * @param modifier entered modifier from other scope
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
            .allowHardware(false)
            .size(Size.ORIGINAL)
            .build()
    )

    var scale by remember { mutableStateOf(START_SCALE) }
    var translationX by remember { mutableStateOf(START_TRANSLATION_COORDINATION) }
    var translationY by remember { mutableStateOf(START_TRANSLATION_COORDINATION) }

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
                        scale = if (scale == START_SCALE) DOUBLE_TAP_SCALE else START_SCALE

                        translationX = if (translationX == START_TRANSLATION_COORDINATION) -(it.x - size.width / 3f)
                        else START_TRANSLATION_COORDINATION

                        translationY = if (translationY == START_TRANSLATION_COORDINATION) -(it.y - size.height / 3f)
                        else START_TRANSLATION_COORDINATION
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

private const val START_TRANSLATION_COORDINATION = 0F
private const val START_SCALE = 1F
private const val DOUBLE_TAP_SCALE = 3F