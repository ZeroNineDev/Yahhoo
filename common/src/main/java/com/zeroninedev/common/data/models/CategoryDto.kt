package com.zeroninedev.common.data.models

import com.zeroninedev.common.domain.models.Category

/**
 * DTO for categories of manga
 *
 * @property id category id
 * @property name name of category
 */
data class CategoryDto(
    val id: String? = null,
    val name: String? = null
)

/**
 * Mapper DTO category to domain
 *
 */
internal fun CategoryDto.toDomain() = Category(
    id = id,
    name = name
)