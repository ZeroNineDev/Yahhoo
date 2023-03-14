package com.zeroninedev.common.data.models

import com.zeroninedev.common.domain.models.UpdatedManga

/**
 * DTO manga in simple list requests
 *
 * @property id manga id
 * @property title name of manga
 * @property imageUrl url link for manga image
 * @property link url for base link manga
 */
data class UpdatedMangaDto(
    val id: String,
    val title: String,
    val imageUrl: String,
    val link: String,
)

/**
 * Mapper DTO simple manga list to domain
 *
 */
internal fun UpdatedMangaDto.toDomain(): UpdatedManga = UpdatedManga(
    id = id,
    title = title,
    imageUrl = imageUrl
)