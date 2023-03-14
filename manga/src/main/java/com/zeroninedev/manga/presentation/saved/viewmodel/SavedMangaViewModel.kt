package com.zeroninedev.manga.presentation.saved.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeroninedev.manga.domain.usecase.GetSavedMangasUseCase
import com.zeroninedev.manga.presentation.lastupdated.screen.LastUpdatedScreenState
import com.zeroninedev.manga.presentation.saved.screen.SavedScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * View Model for popular manga screen
 *
 * @property getSavedMangasUseCase use case for load popular mangas
 */
internal class SavedMangaViewModel(
    private val getSavedMangasUseCase: GetSavedMangasUseCase
) : ViewModel() {

    private val _screenState = MutableStateFlow<SavedScreenState>(SavedScreenState.Loading)
    val screenState = _screenState.asStateFlow()

    /**
     * Reload info about manga when error
     */
    fun updateRequest() {
        loadMangas()
    }

    private fun loadMangas() {
        viewModelScope.launch {
            runCatching { getSavedMangasUseCase() }
                .onSuccess {
                    if (it.isEmpty()) _screenState.value = SavedScreenState.Empty
                    else _screenState.value = SavedScreenState.Success(it)

                }
                .onFailure { LastUpdatedScreenState.Error(it.message.orEmpty()) }
        }
    }
}