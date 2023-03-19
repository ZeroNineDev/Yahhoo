package com.zeroninedev.core_compose.components.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.zeroninedev.core_compose.ui.theme.MediumSize
import com.zeroninedev.core_compose.ui.theme.SmallSize

/**
 * Radio button with text
 *
 * @param items list of pair key and items
 * @param selectedItem selected item
 * @param modifier modifier
 * @param onOptionSelect callback on click with key
 */
@Composable
fun RadioButtonComponent(
    items: Map<Int, String>,
    selectedItem: Int,
    modifier: Modifier = Modifier,
    onOptionSelect: (Int) -> Unit
) {
    var selectedOption by remember { mutableStateOf(selectedItem) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        items.forEach { item ->
            val key = item.key
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (key == selectedOption),
                        onClick = {
                            selectedOption = key
                            onOptionSelect(key)
                        }
                    )
                    .padding(horizontal = MediumSize)
            ) {
                RadioButton(
                    selected = (key == selectedOption),
                    modifier = Modifier.padding(SmallSize),
                    onClick = null
                )
                Text(
                    text = item.value,
                    modifier = Modifier.padding(start = MediumSize)
                )
            }
        }
    }
}
