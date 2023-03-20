package com.zeroninedev.manga.presentation.category.viewmodel


internal sealed class CategoryMangaIntent {

    object UpdateResponse: CategoryMangaIntent()

    data class LoadManga(val categoryId: String, val categoryName: String): CategoryMangaIntent()
}
