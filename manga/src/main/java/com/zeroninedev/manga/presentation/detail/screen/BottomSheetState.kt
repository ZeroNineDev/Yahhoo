package com.zeroninedev.manga.presentation.detail.screen

import com.zeroninedev.common.domain.models.MangaReadStatus

/**
 * States of detail screen
 *
 */
internal sealed class BottomSheetState {

    /** No bottom sheet */
    object None : BottomSheetState()

    /**
     * Bottom sheet with choice status
     *
     * @property data manga status
     */
    data class ReadStatus(val data: MangaReadStatus) : BottomSheetState()

    /**
     * Bottom sheet with read state
     *
     * @property data manga id
     */
    data class WasReadState(val data: String) : BottomSheetState()
}