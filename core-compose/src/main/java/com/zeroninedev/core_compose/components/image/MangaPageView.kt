package com.zeroninedev.core_compose.components.image

import android.graphics.drawable.Drawable
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.detectDragGestures
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import coil.request.ImageRequest.Listener
import com.zeroninedev.core_compose.model.SwipeDirection
import com.zeroninedev.core_compose.model.SwipeDirection.LEFT
import com.zeroninedev.core_compose.model.SwipeDirection.RIGHT
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.abs

/**
 * View to show manga page
 *
 * @param modifier entered modifier from other scope
 * @param url manga page url
 * @param onErrorResult message when image loaded with error
 * @param onSuccessResult return drawable
 * @param onSwipeListener swipe callback with direction
 * @param isSwipeSet set swiping or not
 */
@Composable
fun MangaPageView(
    url: String,
    onErrorResult: (String?) -> Unit,
    onSuccessResult: (Drawable) -> Unit,
    modifier: Modifier = Modifier,
    onSwipeListener: ((SwipeDirection) -> Unit)? = null,
    isSwipeSet: Boolean = false,
) {
    val scrollableState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    var listener by remember {
        mutableStateOf<Listener?>(
            ImageListener(
                onErrorResult = onErrorResult,
                onSuccessResult = onSuccessResult
            )
        )
    }

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
            .wrapContentSize(align = Alignment.Center),
    ) {
        BaseImageLoader(
            url = url,
            listener = listener,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollableState)
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = translationX,
                    translationY = translationY,
                )
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDrag = { change, _ ->
                            val deltaX = change.deltaX()
                            val deltaY = change.deltaY()
                            scroll(coroutineScope, scrollableState, deltaY)

                            if (isSwipeSet && (abs(deltaY) < abs(deltaX))) {
                                swipeCalc(coroutineScope, deltaX) { onSwipeListener?.invoke(it) }
                            }
                        },
                    )
                }
        )
    }
}


private fun swipeCalc(
    coroutineScope: CoroutineScope,
    deltaX: Float,
    swipeListener: (SwipeDirection) -> Unit
) {
    if (debounceJob == null) {
        debounceJob = coroutineScope.launch {
            delay(DELAY_TIME)
            if (DELTA > 200) swipeListener(RIGHT)
            else if (DELTA < -200) swipeListener(LEFT)
            DELTA = 0
            debounceJob = null
        }
    } else {
        DELTA += deltaX.toInt()
    }
}

private fun PointerInputChange.deltaX() = previousPosition.x - position.x
private fun PointerInputChange.deltaY() = previousPosition.y - position.y

private fun scroll(coroutineScope: CoroutineScope, scrollableState: ScrollableState, deltaY: Float) {
    coroutineScope.launch {
        scrollableState.scroll {
            scrollBy(deltaY)
        }
    }
}

private var DELTA = 0
private var debounceJob: Job? = null

private const val DELAY_TIME = 100L

private const val START_TRANSLATION_COORDINATION = 0F
private const val START_SCALE = 1F
private const val DOUBLE_TAP_SCALE = 3F