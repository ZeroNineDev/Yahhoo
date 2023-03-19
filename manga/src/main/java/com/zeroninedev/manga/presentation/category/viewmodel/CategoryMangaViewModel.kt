package com.zeroninedev.manga.presentation.category.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.zeroninedev.manga.domain.usecase.GetCategoryMangaUseCase
import com.zeroninedev.manga.presentation.category.screen.CategoryScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View Model for popular manga screen
 *
 * @property getCategoryMangaUseCase use case for load popular mangas
 */
@HiltViewModel
internal class CategoryMangaViewModel @Inject constructor(
    private val getCategoryMangaUseCase: GetCategoryMangaUseCase
) : ViewModel() {

    private val _screenState = MutableStateFlow<CategoryScreenState>(CategoryScreenState.Loading)
    val screenState = _screenState.asStateFlow()

    private var categoryName: String? = null
    private var categoryId: String? = null


    /**
     * Reload info about manga when error
     */
    fun updateRequest() {
        loadCategory(categoryName.orEmpty(), categoryId.orEmpty())
    }

    /**
     * Load list of popular mangas
     */
    fun loadCategory(categoryName: String, categoryId: String) {
        Log.d("TAG", "$categoryName $categoryId")
        this.categoryName = categoryName
        this.categoryId = categoryId
        viewModelScope.launch {
            runCatching { getCategoryMangaUseCase(categoryId).cachedIn(viewModelScope) }
                .onSuccess { _screenState.value = CategoryScreenState.Success(it, categoryName) }
                .onFailure { _screenState.value = CategoryScreenState.Error(it.message.orEmpty()) }
        }
    }
}