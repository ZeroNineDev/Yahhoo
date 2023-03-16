package com.zeroninedev.manga.presentation.saved.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeroninedev.common.domain.models.MangaReadStatus
import com.zeroninedev.common.domain.models.UpdatedManga
import com.zeroninedev.manga.domain.usecase.GetSavedMangasUseCase
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

    private var list: List<UpdatedManga> = listOf()

    private var status = MangaReadStatus.READ

    /**
     * Reload info about manga when error
     */
    fun updateRequest() {
        loadMangas()
    }

    fun updateSortStatus(status: MangaReadStatus) {
        this.status = status
        _screenState.value = SavedScreenState.Success(list.filter { it.status == status }, status)
    }

    private fun loadMangas() {
        viewModelScope.launch {
            runCatching { getSavedMangasUseCase() }
                .onSuccess { mangas ->
                    if (mangas.isEmpty()) _screenState.value = SavedScreenState.Empty
                    else {
                        list = mangas
                        _screenState.value = SavedScreenState.Success(mangas.filter { it.status == status }, status)
                    }
                }
                .onFailure { SavedScreenState.Error(it.message.orEmpty()) }
        }
    }
}