package com.zeroninedev.manga.presentation.mangachapter.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.zeroninedev.core_compose.components.layout.BoxWithBottomSheet
import com.zeroninedev.core_compose.ui.theme.FiftySize
import com.zeroninedev.core_compose.ui.theme.NormalMediumSize
import com.zeroninedev.core_compose.ui.theme.SmallSize
import com.zeroninedev.core_compose.ui.theme.TinySize

@ExperimentalComposeUiApi
@Composable
internal fun ChapterSelectBottomSheet(
    data: List<Pair<String, String>>,
    onChapterClick: (Pair<String, String>) -> Unit,
    onDismiss: () -> Unit
) {
    BoxWithBottomSheet(
        onDismiss = onDismiss,
        modifier = Modifier.padding(top = FiftySize)
    ) {
        LazyColumn(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
            items(items = data) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(FiftySize)
                        .padding(start = NormalMediumSize, top = TinySize, bottom = TinySize)
                        .background(color = Color.Transparent)
                        .clickable { onChapterClick(it) },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        modifier = Modifier.padding(start = SmallSize),
                        text = it.second,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.primaryVariant
                    )
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
internal fun PageSelectBottomSheet(
    data: Int,
    onPageClick: (Int) -> Unit,
    onDismiss: () -> Unit
) {
    BoxWithBottomSheet(
        onDismiss = onDismiss,
        modifier = Modifier.padding(top = FiftySize)
    ) {
        LazyColumn(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
            repeat(data) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(FiftySize)
                            .padding(start = NormalMediumSize, top = TinySize, bottom = TinySize)
                            .background(color = Color.Transparent)
                            .clickable { onPageClick(it) },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            modifier = Modifier.padding(start = SmallSize),
                            text = "Page ${it + 1}",
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colors.primaryVariant
                        )
                    }
                }
            }
        }
    }
}