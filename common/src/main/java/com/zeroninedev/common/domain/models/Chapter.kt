package com.zeroninedev.common.domain.models

import com.zeroninedev.common.data.dbmodels.ChaptersModel

/**
 * Domain for chapter of manga
 *
 * @property id chapter id
 * @property title name of chapter
 * @property wasRead read manga status
 */
data class Chapter(
    val id: String? = null,
    val title: String? = null,
    val wasRead: Boolean = false
)

/**
 * Function which enrich list with chapters info from chapters from database
 *
 * @param dbChapters chapters info from database
 */
internal fun List<Chapter>.enrichDbData(dbChapters: List<ChaptersModel>): List<Chapter> {

    val dbMap = mutableMapOf<String, Int>()
    dbChapters.forEachIndexed { index, chapter -> dbMap[chapter.idWithoutMangaName] = index }

    return map { chapter ->
        val ind = dbMap[chapter.id]
        if (ind != null)
            Chapter(
                id = chapter.id,
                title = chapter.title,
                wasRead = dbChapters[ind].wasRead
            )
        else chapter
    }
}