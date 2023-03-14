package com.zeroninedev.core_compose.model

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.zeroninedev.core_compose.R
import com.zeroninedev.core_compose.ui.theme.BackgroundCompletedColor
import com.zeroninedev.core_compose.ui.theme.BackgroundAbandonedColor
import com.zeroninedev.core_compose.ui.theme.BackgroundOnHoldColor
import com.zeroninedev.core_compose.ui.theme.BackgroundPlannedColor
import com.zeroninedev.core_compose.ui.theme.BackgroundRereadColor
import com.zeroninedev.core_compose.ui.theme.BackgroundReadColor
import com.zeroninedev.core_compose.ui.theme.BackgroundUnselectedColor
import com.zeroninedev.core_compose.ui.theme.CompletedColor
import com.zeroninedev.core_compose.ui.theme.DroppedColor
import com.zeroninedev.core_compose.ui.theme.OnHoldColor
import com.zeroninedev.core_compose.ui.theme.PlannedColor
import com.zeroninedev.core_compose.ui.theme.ReWatchingColor
import com.zeroninedev.core_compose.ui.theme.WatchingColor

/**
 * Model for ui mapping of read statuses
 *
 */
enum class MangaStatusUi {

    READ,

    IN_PLAN,

    REREAD,

    WAS_READ,

    ON_HOLD,

    ABANDONED,

    UNKNOWN
}

/**
 * Mapper read status to background color
 */
fun MangaStatusUi.toBackgroundColor(): Color =
    when (this) {
        MangaStatusUi.READ -> BackgroundReadColor
        MangaStatusUi.IN_PLAN -> BackgroundPlannedColor
        MangaStatusUi.REREAD -> BackgroundRereadColor
        MangaStatusUi.WAS_READ -> BackgroundCompletedColor
        MangaStatusUi.ON_HOLD -> BackgroundOnHoldColor
        MangaStatusUi.ABANDONED -> BackgroundAbandonedColor
        else -> BackgroundUnselectedColor
    }

/**
 * Mapper read status to drawable res
 */
fun MangaStatusUi.toDrawable(): Int =
    when (this) {
        MangaStatusUi.READ -> R.drawable.ic_play_rate
        MangaStatusUi.IN_PLAN -> R.drawable.ic_planned
        MangaStatusUi.REREAD -> R.drawable.ic_replay
        MangaStatusUi.WAS_READ -> R.drawable.ic_check
        MangaStatusUi.ON_HOLD -> R.drawable.ic_pause_rate
        MangaStatusUi.ABANDONED -> R.drawable.ic_close
        else -> R.drawable.ic_plus
    }

/**
 * Mapper read status to color
 */
@Composable
fun MangaStatusUi.toColor(): Color =
    when (this) {
        MangaStatusUi.READ -> WatchingColor
        MangaStatusUi.IN_PLAN -> PlannedColor
        MangaStatusUi.REREAD -> ReWatchingColor
        MangaStatusUi.WAS_READ -> CompletedColor
        MangaStatusUi.ON_HOLD -> OnHoldColor
        MangaStatusUi.ABANDONED -> DroppedColor
        else -> MaterialTheme.colors.primaryVariant
    }

/**
 * Mapper read status to string performance
 */
fun MangaStatusUi.toMangaPresentationString(): String =
    when (this) {
        MangaStatusUi.READ -> "Читаю"
        MangaStatusUi.IN_PLAN -> "Запланировано"
        MangaStatusUi.REREAD -> "Перечитываю"
        MangaStatusUi.WAS_READ -> "Прочитано"
        MangaStatusUi.ON_HOLD -> "Отложено"
        MangaStatusUi.ABANDONED -> "Брошено"
        else -> "Убрать статус"
    }