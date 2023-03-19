package com.zeroninedev.core_compose.components.image

import android.graphics.drawable.Drawable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.toSize
import coil.request.ImageRequest.Listener

@Composable
fun MangaPageTapView(
    url: String,
    modifier: Modifier = Modifier,
    onRightClick: () -> Unit,
    onLeftClick: () -> Unit,
    onErrorResult: (String?) -> Unit,
    onSuccessResult: (Drawable) -> Unit
) {
    val scrollableState = rememberScrollState()
    var centerPoint by remember { mutableStateOf(Offset.Zero) }

    var scale by remember { mutableStateOf(START_SCALE) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    val scaleAnim by animateFloatAsState(targetValue = scale) {
        if (scale == START_SCALE) offset = Offset.Zero
    }

    var listener by remember {
        mutableStateOf<Listener?>(
            ImageListener(
                onErrorResult = onErrorResult,
                onSuccessResult = onSuccessResult
            )
        )
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                val size = coordinates.size.toSize() / 2.0f
                centerPoint = Offset(size.width, size.height)
            }
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    listener = null
                    scale *= zoom
                    scale = scale.coerceIn(1f, 5f)

                    offset += pan
                    offset = clampOffset(centerPoint, offset, scale)
                }
            }
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        val delta = it.x - centerPoint.x
                        when {
                            delta > 100F -> onRightClick()
                            delta < 100F -> onLeftClick()
                        }
                    },
                    onDoubleTap = {
                        if (scale > DOUBLE_TAP_SCALE) scale = START_SCALE
                        else {
                            scale = DOUBLE_TAP_SCALE
                            offset = (centerPoint - it) * (scale - 1)
                            offset = clampOffset(centerPoint, offset, scale)
                        }
                    }
                )
            }
    ) {
        BaseImageLoader(
            url = url,
            listener = listener,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .clipToBounds()
                .graphicsLayer {
                    translationX = offset.x
                    translationY = offset.y

                    scaleX = scaleAnim
                    scaleY = scaleAnim
                }
                .verticalScroll(scrollableState)
        )
    }
}

private fun clampOffset(centerPoint: Offset, offset: Offset, scale: Float): Offset {
    val maxPosition = centerPoint * (scale - 1)

    return offset.copy(
        x = offset.x.coerceIn(-maxPosition.x, maxPosition.x),
        y = offset.y.coerceIn(-maxPosition.y, maxPosition.y)
    )
}

private const val START_SCALE = 1F
private const val DOUBLE_TAP_SCALE = 3F