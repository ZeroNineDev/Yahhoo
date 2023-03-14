package com.zeroninedev.core_compose.components.layout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.zeroninedev.core_compose.ui.theme.OneSize
import com.zeroninedev.core_compose.ui.theme.RoundedCornerForBottomSheet
import com.zeroninedev.core_compose.ui.theme.ZeroSize

/**
 * Bottom sheet layout
 *
 * @param onDismiss callback on dismiss bottom sheet
 * @param sheetContent content to bottom sheet
 * @param modifier modifier
 * @param isBottomSheetOpen state open bottom sheet
 * @param sheetElevation elevation bottom sheet
 * @param sheetBackgroundColor background color
 * @param sheetBorderStrokeColor border color
 */
@ExperimentalComposeUiApi
@Composable
fun BoxWithBottomSheet(
    onDismiss: () -> Unit,
    sheetContent: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier,
    isBottomSheetOpen: Boolean = true,
    sheetElevation: Dp = OneSize,
    sheetBackgroundColor: Color = MaterialTheme.colors.background,
    sheetBorderStrokeColor: Color = Color.White.copy(alpha = 0.3f),
) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {

        val localDensity = LocalDensity.current

        val parentHeight = with(LocalDensity.current) { constraints.maxHeight.toDp() }

        var isDrag by remember { mutableStateOf(false) }

        var offsetY by remember { mutableStateOf(0f) }

        var sheetHeight by remember { mutableStateOf(ZeroSize) }

        LaunchedEffect(key1 = offsetY) {
            if (offsetY.dp > sheetHeight) {
                onDismiss()
            }
        }

        val sheetOffset: Dp by animateDpAsState(
            targetValue =
            when {
                isBottomSheetOpen && !isDrag -> {
                    parentHeight - sheetHeight
                }
                isBottomSheetOpen && isDrag -> {
                    parentHeight + offsetY.dp
                }
                else -> {
                    parentHeight + sheetHeight
                }
            },
            animationSpec = tween(ANIMATION_TIME)
        )


        AnimatedVisibility(
            visible = isBottomSheetOpen,
            enter = fadeIn(animationSpec = tween(ANIMATION_TIME)),
            exit = fadeOut(animationSpec = tween(ANIMATION_TIME))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colors.background.copy(0.6F))
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                onDismiss()
                            }
                        )
                    }
            )

            Card(
                modifier = modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .align(alignment = Alignment.TopCenter)
                    .offset(y = sheetOffset)
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragStart = {
                                offsetY = 0f
                                isDrag = true
                            },
                            onDrag = { change, dragAmount ->
                                change.consume()
                                isDrag = true
                                offsetY += dragAmount.y
                            },
                            onDragEnd = {
                                isDrag = false
                            },
                            onDragCancel = {
                                isDrag = false
                            }
                        )
                    },
                backgroundColor = sheetBackgroundColor,
                border = BorderStroke(
                    width = OneSize,
                    color = sheetBorderStrokeColor
                ),
                elevation = sheetElevation,
                shape = RoundedCornerForBottomSheet
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .onGloballyPositioned {
                            sheetHeight = with(localDensity) {
                                it.size.height.toDp()
                            }
                        },
                    content = sheetContent
                )
            }
        }
    }
}

private const val ANIMATION_TIME = 700