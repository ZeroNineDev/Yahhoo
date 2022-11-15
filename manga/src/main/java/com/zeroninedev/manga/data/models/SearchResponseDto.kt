package com.zeroninedev.manga.data.models

internal data class SearchResponseDto(
    val mangas: List<UpdatedMangaDto>,
    val pagesMax: Int
)

