package com.zeroninedev.common.domain.models

import com.zeroninedev.common.data.dbmodels.ChaptersModel
import com.zeroninedev.common.data.dbmodels.MangaModel
import com.zeroninedev.common.domain.models.MangaReadStatus.UNKNOWN
import com.zeroninedev.common.domain.models.MangaReadStatus.valueOf

data class Manga(
    val id: String? = null,
    val title: String? = null,
    val image: String? = null,
    val category: List<Category> = listOf(),
    val status: String? = null,
    val genre: String? = null,
    val anotherTitle: String? = null,
    val link: String? = null,
    val author: String? = null,
    val drawer: String? = null,
    val views: String? = null,
    val description: String? = null,
    val mangaStatus: MangaReadStatus = UNKNOWN,
    val translator: String? = null,
    val chapters: List<Chapter> = listOf(),
)

internal fun Manga.enrichDbData(dbData: MangaModel, dbChapters: List<ChaptersModel>) = copy(
    mangaStatus = valueOf(dbData.status),
    chapters = chapters.enrichDbData(dbChapters)
)

internal fun Manga.toDatabaseModel(): MangaModel = MangaModel(
    id = id.orEmpty(),
    title = title.orEmpty(),
    imageUrl = image.orEmpty(),
    status = mangaStatus.name,
)