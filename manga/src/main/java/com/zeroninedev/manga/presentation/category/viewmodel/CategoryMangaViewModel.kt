package com.zeroninedev.manga.presentation.category.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.zeroninedev.common.base.BaseViewModel
import com.zeroninedev.manga.domain.usecase.GetCategoryMangaUseCase
import com.zeroninedev.manga.presentation.category.screen.CategoryScreenState
import com.zeroninedev.manga.presentation.category.viewmodel.CategoryMangaIntent.LoadManga
import com.zeroninedev.manga.presentation.category.viewmodel.CategoryMangaIntent.UpdateResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * View Model for popular manga screen
 *
 * @property getCategoryMangaUseCase use case for load popular mangas
 */
@HiltViewModel
internal class CategoryMangaViewModel @Inject constructor(
    private val getCategoryMangaUseCase: GetCategoryMangaUseCase
) : BaseViewModel<CategoryScreenState>(CategoryScreenState.Loading) {

    private var categoryName: String? = null
    private var categoryId: String? = null

    fun processIntent(intent: CategoryMangaIntent) = when (intent) {
        is LoadManga -> loadCategory(intent.categoryName, intent.categoryId)
        UpdateResponse -> updateRequest()
    }

    private fun updateRequest() {
        loadCategory(categoryName.orEmpty(), categoryId.orEmpty())
    }

    private fun loadCategory(categoryName: String, categoryId: String) {
        this.categoryName = categoryName
        this.categoryId = categoryId
        runCatching { getCategoryMangaUseCase(categoryId).cachedIn(viewModelScope) }
            .onSuccess { _screenState.value = CategoryScreenState.Success(it, categoryName) }
            .onFailure { _screenState.value = CategoryScreenState.Error(it.message.orEmpty()) }
    }
}