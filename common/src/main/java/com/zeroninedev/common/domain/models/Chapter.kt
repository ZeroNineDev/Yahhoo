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
    var outputList = zip(dbChapters) { api, db -> Chapter(id = api.id, title = api.title, wasRead = db.wasRead) }
    if (outputList.size != size) outputList = outputList.plus(subList(outputList.size, size))
    return outputList
}