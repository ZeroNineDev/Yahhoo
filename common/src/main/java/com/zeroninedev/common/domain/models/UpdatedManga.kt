package com.zeroninedev.common.domain.models

/**
 * Domain manga in simple list requests
 *
 * @property id manga id
 * @property title name of manga
 * @property imageUrl url link for manga image
 */
data class UpdatedManga(
    val id: String,
    val title: String,
    val imageUrl: String,
)