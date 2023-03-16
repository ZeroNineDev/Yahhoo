package com.zeroninedev.manga.presentation.mangachapter.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.zeroninedev.core_compose.components.button.TransparentButton
import com.zeroninedev.core_compose.components.image.MangaPageView
import com.zeroninedev.core_compose.components.text.ChapterPagesTextView
import com.zeroninedev.core_compose.model.SwipeDirection.RIGHT

/**
 * Reader manga chapter view
 *
 * @param chapterPage list of chapter pages
 * @param prevPart callback on load prev chapter
 * @param nextPart callback on load next chapter
 * @param afterHalfPart callback on watched half manga chapter
 * @param isSwipeEnabled state manga switch pages
 * @param onErrorAction callback when image was loaded with error
 */
@Composable
internal fun MangaChapterView(
    chapterPage: List<String>,
    prevPart: () -> Unit,
    nextPart: () -> Unit,
    afterHalfPart: () -> Unit,
    isSwipeEnabled: Boolean = false,
    onErrorAction: (String?) -> Unit
) {
    var page by remember { mutableStateOf(0) }
    if (page > chapterPage.size / 2) afterHalfPart()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        MangaPageView(
            url = chapterPage[page],
            onErrorResult = { onErrorAction(it) },
            onSuccessResult = {},
            isSwipeSet = isSwipeEnabled,
            onSwipeListener = {
                if (it == RIGHT) {
                    if (page < chapterPage.lastIndex) page += 1
                    else nextPart()
                } else {
                    if (page != 0) page -= 1
                    else prevPart()
                }
            }
        )

        Column {
            Row(modifier = Modifier.weight(1f)) {
                if (!isSwipeEnabled) {
                    TransparentButton {
                        if (page != 0) page -= 1
                        else prevPart()
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                if (!isSwipeEnabled) {
                    TransparentButton {
                        if (page < chapterPage.lastIndex) page += 1
                        else nextPart()
                    }
                }

            }

            ChapterPagesTextView(text = "${page + 1} / ${chapterPage.size}")
        }
    }
}