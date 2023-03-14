package com.zeroninedev.manga.presentation.detail.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.zeroninedev.common.domain.models.MangaReadStatus
import com.zeroninedev.core_compose.components.layout.BoxWithBottomSheet
import com.zeroninedev.core_compose.model.toBackgroundColor
import com.zeroninedev.core_compose.model.toColor
import com.zeroninedev.core_compose.model.toDrawable
import com.zeroninedev.core_compose.model.toMangaPresentationString
import com.zeroninedev.core_compose.ui.theme.FiftySize
import com.zeroninedev.core_compose.ui.theme.NormalMediumSize
import com.zeroninedev.core_compose.ui.theme.SmallSize
import com.zeroninedev.core_compose.ui.theme.TinySize
import com.zeroninedev.manga.domain.model.allMangaStatuses
import com.zeroninedev.manga.domain.model.toDomainStatus
import com.zeroninedev.manga.domain.model.toUiStatus

/**
 * View of bottom sheet with read status
 *
 * @param currentMangaStatus current read status
 * @param onChangeStatus callback on change read status
 * @param onDismiss callback on dismiss bottom sheet
 */
@ExperimentalComposeUiApi
@Composable
internal fun MangaStatusBottomSheetView(
    currentMangaStatus: MangaReadStatus,
    onChangeStatus: (MangaReadStatus) -> Unit,
    onDismiss: () -> Unit
) {
    BoxWithBottomSheet(
        onDismiss = onDismiss,
        sheetContent = {

            LazyColumn(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            ) {

                items(items = allMangaStatuses().map { it.toUiStatus() }) { item ->

                    val currentState = currentMangaStatus.toUiStatus() == item

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(FiftySize)
                            .padding(start = NormalMediumSize, top = TinySize, bottom = TinySize)
                            .background(color = if (currentState) item.toBackgroundColor() else Color.Transparent)
                            .clickable { onChangeStatus(item.toDomainStatus()) },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            modifier = Modifier.padding(start = NormalMediumSize),
                            painter = painterResource(id = item.toDrawable()),
                            contentDescription = null,
                            tint = if (currentState) item.toColor() else MaterialTheme.colors.primaryVariant
                        )

                        Text(
                            modifier = Modifier.padding(start = SmallSize),
                            text = item.toMangaPresentationString(),
                            textAlign = TextAlign.Center,
                            color = if (currentState) item.toColor() else MaterialTheme.colors.primaryVariant
                        )
                    }
                }
            }
        }
    )
}

/**
 * View of bottom sheet with read status
 *
 * @param onChangeStatus callback on change read state
 * @param onDismiss callback on dismiss bottom sheet
 */
@ExperimentalComposeUiApi
@Composable
internal fun MangaStatusBottomSheetView(
    onChangeStatus: (Boolean) -> Unit,
    onDismiss: () -> Unit
) {
    BoxWithBottomSheet(
        onDismiss = onDismiss,
        sheetContent = {

            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(FiftySize)
                        .padding(start = NormalMediumSize, top = TinySize, bottom = TinySize)
                        .background(color = Color.Transparent)
                        .clickable { onChangeStatus(true) },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        modifier = Modifier.padding(start = NormalMediumSize),
                        painter = painterResource(id = com.zeroninedev.core_compose.R.drawable.ic_check),
                        contentDescription = null,
                        tint = MaterialTheme.colors.primaryVariant
                    )

                    Text(
                        modifier = Modifier.padding(start = SmallSize),
                        text = stringResource(id = com.zeroninedev.core_compose.R.string.manga_state_ui_was_read),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.primaryVariant
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(FiftySize)
                        .padding(start = NormalMediumSize, top = TinySize, bottom = TinySize)
                        .background(color = Color.Transparent)
                        .clickable { onChangeStatus(false) },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        modifier = Modifier.padding(start = NormalMediumSize),
                        painter = painterResource(id = com.zeroninedev.core_compose.R.drawable.ic_close),
                        contentDescription = null,
                        tint = MaterialTheme.colors.primaryVariant
                    )

                    Text(
                        modifier = Modifier.padding(start = SmallSize),
                        text = stringResource(id = com.zeroninedev.core_compose.R.string.manga_state_ui_none),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.primaryVariant
                    )
                }
            }
        }
    )
}