package com.zeroninedev.manga.domain.models

import com.zeroninedev.manga.data.models.UpdatedMangaDto

internal data class UpdatedManga(
    val id: String,
    val title: String,
    val imageUrl: String,
)

internal fun UpdatedMangaDto.toDomain(): UpdatedManga = UpdatedManga(
    id = id,
    title = title,
    imageUrl = imageUrl
)
