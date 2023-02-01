package com.zeroninedev.common.domain.models

import com.zeroninedev.common.data.models.UpdatedMangaDto

data class UpdatedManga(
    val id: String,
    val title: String,
    val imageUrl: String,
)

internal fun UpdatedMangaDto.toDomain(): UpdatedManga = UpdatedManga(
    id = id,
    title = title,
    imageUrl = imageUrl
)
