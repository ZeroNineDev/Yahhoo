package com.zeroninedev.manga.presentation.mangachapter.view

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.zeroninedev.core_compose.components.button.RoundedButton
import com.zeroninedev.core_compose.components.button.SimpleButton
import com.zeroninedev.core_compose.components.image.MangaPageTapView
import com.zeroninedev.core_compose.components.image.MangaPageView
import com.zeroninedev.core_compose.components.image.MangaPageViewWithoutScroll
import com.zeroninedev.core_compose.model.SwipeDirection.RIGHT
import com.zeroninedev.core_compose.ui.theme.FiftySize
import com.zeroninedev.core_compose.ui.theme.SmallSize

/**
 * Reader manga chapter view
 *
 * @param chapterPage list of chapter pages
 * @param prevPart callback on load prev chapter
 * @param nextPart callback on load next chapter
 * @param afterHalfPart callback on watched half manga chapter
 * @param onErrorAction callback when image was loaded with error
 */
@Composable
internal fun MangaChapterScrollView(
    chapterPage: List<String>,
    prevPart: () -> Unit,
    nextPart: () -> Unit,
    chapterName: String,
    afterHalfPart: () -> Unit,
    onErrorAction: (String?) -> Unit,
    onChapterClick: () -> Unit,
    onPageClick: () -> Unit
) {
    val listState = rememberLazyListState()
    val currentItem = remember { derivedStateOf { listState.firstVisibleItemIndex } }
    if (currentItem.value >= (chapterPage.lastIndex / 2)) afterHalfPart()
    val horizontalAnim by animateDpAsState(targetValue = if (currentItem.value + 1 >= chapterPage.lastIndex) FiftySize else SmallSize)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            state = listState
        ) {
            items(items = chapterPage) { currentPage ->
                MangaPageViewWithoutScroll(
                    url = currentPage,
                    onErrorResult = { onErrorAction(it) },
                    onSuccessResult = {}
                )
            }
            item {
                Row(
                    modifier = Modifier
                        .height(FiftySize)
                        .fillParentMaxWidth()
                ) {
                    SimpleButton(
                        text = stringResource(id = com.zeroninedev.core_compose.R.string.text_previous_button),
                        modifier = Modifier.wrapContentSize(),
                        onButtonClick = prevPart
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    SimpleButton(
                        text = stringResource(id = com.zeroninedev.core_compose.R.string.text_next_button),
                        modifier = Modifier.wrapContentSize(),
                        onButtonClick = nextPart
                    )
                }
            }
        }
        LayoutWithButtonInBottom(
            chapterName = chapterName,
            currentPage = currentItem.value,
            maxPage = chapterPage.size,
            modifier = Modifier.padding(start = SmallSize, bottom = horizontalAnim),
            onPageClick = onPageClick,
            onChapterClick = onChapterClick
        )
    }
}

@Composable
fun MangaChapterTapView(
    chapterPageUrl: String,
    currentPage: Int,
    maxPage: Int,
    chapterName: String,
    prevPart: () -> Unit,
    nextPart: () -> Unit,
    onPageClick: () -> Unit,
    onChapterClick: () -> Unit,
    onErrorAction: (String?) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        MangaPageTapView(
            url = chapterPageUrl,
            onErrorResult = { onErrorAction(it) },
            onSuccessResult = {},
            onLeftClick = prevPart,
            onRightClick = nextPart
        )
        LayoutWithButtonInBottom(
            chapterName = chapterName,
            currentPage = currentPage,
            maxPage = maxPage,
            onPageClick = onPageClick,
            onChapterClick = onChapterClick
        )
    }
}

@Composable
fun MangaChapterSwipeView(
    chapterPageUrl: String,
    currentPage: Int,
    maxPage: Int,
    chapterName: String,
    prevPart: () -> Unit,
    nextPart: () -> Unit,
    onPageClick: () -> Unit,
    onChapterClick: () -> Unit,
    onErrorAction: (String?) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        MangaPageView(
            url = chapterPageUrl,
            onErrorResult = { onErrorAction(it) },
            onSuccessResult = {},
            isSwipeSet = true,
            onSwipeListener = {
                if (it == RIGHT) nextPart()
                else prevPart()
            }
        )
        LayoutWithButtonInBottom(
            chapterName = chapterName,
            currentPage = currentPage,
            maxPage = maxPage,
            onPageClick = onPageClick,
            onChapterClick = onChapterClick
        )
    }
}

@Composable
private fun LayoutWithButtonInBottom(
    currentPage: Int,
    maxPage: Int,
    chapterName: String,
    modifier: Modifier = Modifier,
    onChapterClick: () -> Unit,
    onPageClick: () -> Unit,
) {
    Column {
        RoundedButton(
            text = chapterName,
            modifier = modifier.padding(
                WindowInsets.systemBars
                    .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top)
                    .asPaddingValues()
            ),
            onButtonClick = onChapterClick
        )
        Spacer(modifier = Modifier.weight(1f))
        RoundedButton(
            text = "${currentPage + 1} / $maxPage",
            modifier = modifier
                .padding(
                    WindowInsets.systemBars
                        .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom)
                        .asPaddingValues()
                ),
            onButtonClick = onPageClick
        )
    }
}