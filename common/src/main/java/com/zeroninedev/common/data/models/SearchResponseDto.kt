package com.zeroninedev.common.data.models

/**
 * DTO search response by name
 *
 * @property mangas list of mangas which contains name
 * @property pagesMax last page
 */
data class SearchResponseDto(
    val mangas: List<UpdatedMangaDto>,
    val pagesMax: Int
)