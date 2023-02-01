package com.zeroninedev.common.domain.models

import com.zeroninedev.common.data.models.CategoryDto
import com.zeroninedev.common.data.models.ChapterDto
import com.zeroninedev.common.data.models.MangaDto

data class Manga(
    val id: String? = null,
    val title: String? = null,
    val image: String? = null,
    val category: List<CategoryDto> = listOf(),
    val status: String? = null,
    val genre: String? = null,
    val anotherTitle: String? = null,
    val link: String? = null,
    val author: String? = null,
    val drawer: String? = null,
    val views: String? = null,
    val description: String? = null,
    val translator: String? = null,
    val chapters: List<ChapterDto> = listOf(),
)

internal fun MangaDto.toDomain(): Manga = Manga(
    id = id,
    title = title,
    image = image,
    category = category,
    status = status,
    genre = genre,
    anotherTitle = anotherTitle,
    link = link,
    author = author,
    drawer = drawer,
    views = views,
    description = description,
    translator = translator,
    chapters = chapters
)
