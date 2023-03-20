package com.zeroninedev.manga.presentation.popular.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.zeroninedev.common.base.BaseViewModel
import com.zeroninedev.manga.domain.usecase.GetPopularMangaUseCase
import com.zeroninedev.manga.presentation.popular.screen.PopularScreenState
import com.zeroninedev.manga.presentation.popular.viewmodel.PopularMangaIntent.LoadManga
import com.zeroninedev.manga.presentation.popular.viewmodel.PopularMangaIntent.UpdateResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * View Model for popular manga screen
 *
 * @property getPopularMangaUseCase use case for load popular mangas
 */
@HiltViewModel
internal class PopularMangaViewModel @Inject constructor(
    private val getPopularMangaUseCase: GetPopularMangaUseCase
) : BaseViewModel<PopularScreenState>(PopularScreenState.Loading) {

    init {
        loadMangas()
    }

    fun processIntent(intent: PopularMangaIntent) = when(intent) {
        LoadManga -> loadMangas()
        UpdateResponse -> loadMangas()
    }

    /**
     * Load list of popular mangas
     */
    private fun loadMangas() {
        runCatching { getPopularMangaUseCase().cachedIn(viewModelScope) }
            .onSuccess { _screenState.value = PopularScreenState.Success(it) }
            .onFailure { _screenState.value = PopularScreenState.Error(it.message.orEmpty()) }
    }
}