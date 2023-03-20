package com.zeroninedev.manga.presentation.saved.viewmodel

import androidx.lifecycle.viewModelScope
import com.zeroninedev.common.base.BaseViewModel
import com.zeroninedev.common.domain.models.MangaReadStatus
import com.zeroninedev.common.domain.models.UpdatedManga
import com.zeroninedev.manga.domain.usecase.GetSavedMangasUseCase
import com.zeroninedev.manga.presentation.saved.screen.SavedScreenState
import com.zeroninedev.manga.presentation.saved.viewmodel.SavedMangaIntent.ChangeFilterStatus
import com.zeroninedev.manga.presentation.saved.viewmodel.SavedMangaIntent.LoadManga
import com.zeroninedev.manga.presentation.saved.viewmodel.SavedMangaIntent.UpdateResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View Model for popular manga screen
 *
 * @property getSavedMangasUseCase use case for load popular mangas
 */
@HiltViewModel
internal class SavedMangaViewModel @Inject constructor(
    private val getSavedMangasUseCase: GetSavedMangasUseCase
) : BaseViewModel<SavedScreenState>(SavedScreenState.Loading) {

    private var list: List<UpdatedManga> = listOf()

    private var status = MangaReadStatus.READ

    fun processIntent(intent: SavedMangaIntent) = when (intent) {
        is ChangeFilterStatus -> updateSortStatus(intent.status)
        LoadManga -> loadMangas()
        UpdateResponse -> loadMangas()
    }

    private fun updateSortStatus(status: MangaReadStatus) {
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