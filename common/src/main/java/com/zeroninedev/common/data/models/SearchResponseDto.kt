package com.zeroninedev.common.data.models

data class SearchResponseDto(
    val mangas: List<UpdatedMangaDto>,
    val pagesMax: Int
)

