package com.zeroninedev.core_compose.components.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import com.zeroninedev.core_compose.components.input.SearchInputText

@Composable
fun SearchLayout(
    onTextChange: (String) -> Unit,
    onClearQuery: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Column() {
        SearchInputText(
            showClearIcon = true,
            onQueryTextChange = onTextChange,
            onClearClick = onClearQuery
        )
        Column(content = content)
    }
}