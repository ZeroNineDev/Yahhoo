package com.zeroninedev.manga.presentation.popular.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.zeroninedev.manga.domain.usecase.GetPopularMangaUseCase
import com.zeroninedev.manga.presentation.popular.screen.PopularScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View Model for popular manga screen
 *
 * @property getPopularMangaUseCase use case for load popular mangas
 */
internal class PopularMangaViewModel @Inject constructor(
    private val getPopularMangaUseCase: GetPopularMangaUseCase
) : ViewModel() {

    private val _screenState = MutableStateFlow<PopularScreenState>(PopularScreenState.Loading)
    val screenState = _screenState.asStateFlow()

    init {
        loadMangas()
    }

    /**
     * Reload info about manga when error
     */
    fun updateRequest() {
        loadMangas()
    }

    /**
     * Load list of popular mangas
     */
    private fun loadMangas() {
        viewModelScope.launch {
            runCatching { getPopularMangaUseCase().cachedIn(viewModelScope) }
                .onSuccess { _screenState.value = PopularScreenState.Success(it) }
                .onFailure { _screenState.value = PopularScreenState.Error(it.message.orEmpty()) }
        }
    }
}