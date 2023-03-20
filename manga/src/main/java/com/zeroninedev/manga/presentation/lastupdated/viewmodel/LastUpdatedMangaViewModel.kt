package com.zeroninedev.manga.presentation.lastupdated.viewmodel

import androidx.lifecycle.viewModelScope
import com.zeroninedev.common.base.BaseViewModel
import com.zeroninedev.manga.domain.usecase.GetLastUpdatedMangaUseCase
import com.zeroninedev.manga.presentation.lastupdated.screen.LastUpdatedScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View Model for last updated screen
 *
 * @property getLastUpdatedMangaUseCase use case for load last updated manga
 */
@HiltViewModel
internal class LastUpdatedMangaViewModel @Inject constructor(
    private val getLastUpdatedMangaUseCase: GetLastUpdatedMangaUseCase
) : BaseViewModel<LastUpdatedScreenState>(LastUpdatedScreenState.Loading) {

    init {
        loadMangas()
    }

    fun processIntent(intent: LastUpdatedMangaIntent) = when (intent) {
        LastUpdatedMangaIntent.LoadManga -> loadMangas()
        LastUpdatedMangaIntent.UpdateResponse -> updateRequest()
    }

    /**
     * Reload info about manga when error
     */
    private fun updateRequest() {
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