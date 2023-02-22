package com.zeroninedev.manga.presentation.lastupdated.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeroninedev.manga.domain.usecase.GetLastUpdatedMangaUseCase
import com.zeroninedev.manga.presentation.lastupdated.screen.LastUpdatedScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View Model for last updated screen
 *
 * @property getLastUpdatedMangaUseCase use case for load last updated manga
 */
internal class LastUpdatedMangaViewModel @Inject constructor(
    private val getLastUpdatedMangaUseCase: GetLastUpdatedMangaUseCase
) : ViewModel() {

    private val _screenState = MutableStateFlow<LastUpdatedScreenState>(LastUpdatedScreenState.Loading)
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

    private fun loadMangas() {
        viewModelScope.launch {
            runCatching { getLastUpdatedMangaUseCase() }
                .onSuccess { _screenState.value = LastUpdatedScreenState.Success(it) }
                .onFailure { LastUpdatedScreenState.Error(it.message.orEmpty()) }
        }
    }
}