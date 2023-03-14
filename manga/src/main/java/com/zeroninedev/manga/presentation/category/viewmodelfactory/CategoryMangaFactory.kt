package com.zeroninedev.manga.presentation.category.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zeroninedev.manga.domain.usecase.GetCategoryMangaUseCase
import com.zeroninedev.manga.presentation.category.viewmodel.CategoryMangaViewModel
import javax.inject.Inject

internal class CategoryMangaFactory @Inject constructor(
    private val getCategoryMangaUseCase: GetCategoryMangaUseCase
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        CategoryMangaViewModel(getCategoryMangaUseCase) as T
}