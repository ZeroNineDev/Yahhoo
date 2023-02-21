package com.zeroninedev.core_compose.components.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
inline fun RowWithWrap(
    modifier: Modifier = Modifier,
    verticalSpacer: Dp = 0.dp,
    horizontalSpacer: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    Box(modifier) {
        Layout(
            content = content,
            measurePolicy = rowWithWrapMesaurePolicy(verticalSpacer, horizontalSpacer)
        )
    }
}

@Composable
fun rowWithWrapMesaurePolicy(
    verticalSpacer: Dp = 0.dp,
    horizontalSpacer: Dp = 0.dp
): MeasurePolicy = remember(verticalSpacer, horizontalSpacer) {
    MeasurePolicy { measurables: List<Measurable>, constraints: Constraints ->
        val positions = rowWithWrapRelativePositions(constraints, measurables, verticalSpacer, horizontalSpacer)
        val width = maxOf(positions.maxOf { it.maxXCoordinate }, constraints.minWidth)
        val height = minOf(maxOf(positions.maxOf { it.maxYCoordinate }, constraints.minHeight), constraints.maxHeight)
        layout(width, height) {
            for ((placeable, dx, dy) in positions) {
                placeable.placeRelative(dx, dy)
            }
        }
    }
}

private fun MeasureScope.rowWithWrapRelativePositions(
    constraints: Constraints,
    measurables: List<Measurable>,
    verticalSpacer: Dp,
    horizontalSpacer: Dp
): List<PlaceableRelativePosition> {
    val res = mutableListOf<PlaceableRelativePosition>()
    var x = 0
    var y = 0
    var maxHeight = -1

    for (measurable in measurables) {
        val placeable = measurable.measure(constraints)
        if (placeable.width + x > constraints.maxWidth) {
            y += maxHeight + verticalSpacer.roundToPx()
            x = 0
            maxHeight = -1
        }
        res += PlaceableRelativePosition(placeable, x, y)
        x += placeable.width + horizontalSpacer.roundToPx()
        maxHeight = maxOf(maxHeight, placeable.height)
    }

    return res
}

private data class PlaceableRelativePosition(val placeable: Placeable, val dx: Int, val dy: Int)

private val PlaceableRelativePosition.maxXCoordinate: Int
    get() = dx + placeable.width

private val PlaceableRelativePosition.maxYCoordinate: Int
    get() = dy + placeable.height