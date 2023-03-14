package com.zeroninedev.common.data.models

import com.zeroninedev.common.domain.models.Chapter

/**
 * DTO for chapter of manga
 *
 * @property id chapter id
 * @property title name of chapter
 */
data class ChapterDto(
    val id: String? = null,
    val title: String? = null,
)

/**
 * Mapper DTO chapter to domain
 *
 */
internal fun ChapterDto.toDomain() = Chapter(
    id = id,
    title = title
)
