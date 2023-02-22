package com.zeroninedev.common.data.models

import com.zeroninedev.common.domain.models.Manga

/**
 * DTO for detail in manga
 *
 * @property id manga id
 * @property title name manga
 * @property image url for image
 * @property category categories dto
 * @property status status of manga
 * @property genre genre of manga
 * @property anotherTitle another name of manga
 * @property link link in base source
 * @property author author of manga
 * @property drawer drawer of manga
 * @property views all views of manga
 * @property description description text of manga
 * @property translator translators of manga
 * @property chapters chapters dto
 */
data class MangaDto(
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

/**
 * Mapper DTO manga to domain
 *
 */
internal fun MangaDto.toDomain(): Manga = Manga(
    id = id,
    title = title,
    image = image,
    category = category.map { it.toDomain() },
    status = status,
    genre = genre,
    anotherTitle = anotherTitle,
    link = link,
    author = author,
    drawer = drawer,
    views = views,
    description = description,
    translator = translator,
    chapters = chapters.map { it.toDomain() }
)