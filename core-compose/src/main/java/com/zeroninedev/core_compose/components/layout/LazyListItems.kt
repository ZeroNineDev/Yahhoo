package com.zeroninedev.core_compose.components.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

fun <T> LazyListScope.gridItems(
    items: List<T>,
    columnCount: Int,
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    itemContent: @Composable BoxScope.(item: T) -> Unit
) {
    val size = items.count()

    val rows = if (size == 0) 0 else 1 + (size - 1) / columnCount

    items(rows) { rowIndex ->
        Row(
            modifier = modifier,
            horizontalArrangement = horizontalArrangement
        ) {
            for (columnIndex in 0 until columnCount) {

                val itemIndex = rowIndex * columnCount + columnIndex

                if (itemIndex < size) {
                    Box(
                        modifier = Modifier.weight(weight = 1F),
                        propagateMinConstraints = true
                    ) {
                        itemContent(items[itemIndex])
                    }
                } else {
                    Spacer(
                        modifier = Modifier.weight(weight = 1F)
                    )
                }
            }
        }
    }
}