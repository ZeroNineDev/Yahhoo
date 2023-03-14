package com.zeroninedev.manga.domain.model

import com.zeroninedev.common.domain.models.MangaReadStatus
import com.zeroninedev.core_compose.model.MangaStatusUi

/**
 * @return list of all manga read statuses
 */
internal fun allMangaStatuses(): List<MangaReadStatus> = MangaReadStatus.values().toList()

/**
 * @return list of all manga read statuses
 */
internal fun allSavedMangaStatuses(): List<MangaReadStatus> = listOf(
    MangaReadStatus.READ,
    MangaReadStatus.IN_PLAN,
    MangaReadStatus.REREAD,
    MangaReadStatus.WAS_READ,
    MangaReadStatus.ON_HOLD,
    MangaReadStatus.ABANDONED
)

/**
 * Mapper Domain manga read status in Ui status
 */
internal fun MangaReadStatus.toUiStatus() =
    when (this) {
        MangaReadStatus.READ -> MangaStatusUi.READ
        MangaReadStatus.IN_PLAN -> MangaStatusUi.IN_PLAN
        MangaReadStatus.REREAD -> MangaStatusUi.REREAD
        MangaReadStatus.WAS_READ -> MangaStatusUi.WAS_READ
        MangaReadStatus.ON_HOLD -> MangaStatusUi.ON_HOLD
        MangaReadStatus.ABANDONED -> MangaStatusUi.ABANDONED
        MangaReadStatus.UNKNOWN -> MangaStatusUi.UNKNOWN
    }

/**
 * Mapper Ui manga read status in Domain status
 */
internal fun MangaStatusUi.toDomainStatus() =
    when (this) {
        MangaStatusUi.READ -> MangaReadStatus.READ
        MangaStatusUi.IN_PLAN -> MangaReadStatus.IN_PLAN
        MangaStatusUi.REREAD -> MangaReadStatus.REREAD
        MangaStatusUi.WAS_READ -> MangaReadStatus.WAS_READ
        MangaStatusUi.ON_HOLD -> MangaReadStatus.ON_HOLD
        MangaStatusUi.ABANDONED -> MangaReadStatus.ABANDONED
        MangaStatusUi.UNKNOWN -> MangaReadStatus.UNKNOWN
    }