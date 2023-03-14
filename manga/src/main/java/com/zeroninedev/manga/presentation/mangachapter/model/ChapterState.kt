package com.zeroninedev.manga.presentation.mangachapter.model

/**
 * Chapter direction state
 *
 * @property value value for list to get item
 */
internal enum class ChapterState(val value: Int) {

    /** Load next page */
    NEXT_PAGE(-1),

    /** Load prev page */
    PREV_PAGE(1)
}