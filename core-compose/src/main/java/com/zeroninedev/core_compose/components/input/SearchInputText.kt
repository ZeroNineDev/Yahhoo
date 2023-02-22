package com.zeroninedev.core_compose.components.input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.zeroninedev.core_compose.R

/**
 * Input text for search with icon
 *
 * @param modifier entered modifier from other scope
 * @param showClearIcon state to show icon for clear search input
 * @param onQueryTextChange callback on text change
 * @param onClearClick callback on clear input text click
 */
@Composable
fun SearchInputText(
    modifier: Modifier = Modifier,
    showClearIcon: Boolean,
    onQueryTextChange: (String) -> Unit,
    onClearClick: () -> Unit
) {
    var query by remember { mutableStateOf(String()) }

    TextField(
        value = query,
        onValueChange = { onQueryChanged ->
            query = onQueryChanged
            onQueryTextChange(onQueryChanged)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                tint = MaterialTheme.colors.onBackground,
                contentDescription = stringResource(id = R.string.search_icon_content_description)
            )
        },
        trailingIcon = {
            if (showClearIcon) {
                IconButton(onClick = {
                    query = String()
                    onClearClick()
                }) {
                    Icon(
                        imageVector = Icons.Rounded.Clear,
                        tint = MaterialTheme.colors.onBackground,
                        contentDescription = stringResource(id = R.string.clear_icon_content_description)
                    )
                }
            }
        },
        maxLines = MAX_LINES_FOR_SEARCH,
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        placeholder = { Text(text = stringResource(R.string.hint_search_query)) },
        textStyle = MaterialTheme.typography.subtitle1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background, shape = RectangleShape)
    )
}

private const val MAX_LINES_FOR_SEARCH = 1