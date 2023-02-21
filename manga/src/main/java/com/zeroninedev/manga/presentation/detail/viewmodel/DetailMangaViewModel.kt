package com.zeroninedev.manga.presentation.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeroninedev.manga.domain.usecase.GetDetailMangaUseCase
import com.zeroninedev.manga.domain.usecase.SaveChaptersUseCase
import com.zeroninedev.manga.presentation.detail.screen.DetailScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class DetailMangaViewModel @Inject constructor(
    private val getDetailMangaUseCase: GetDetailMangaUseCase,
    private val saveChaptersUseCase: SaveChaptersUseCase
) : ViewModel() {

    private val _screenState = MutableStateFlow<DetailScreenState>(DetailScreenState.Loading)
    val screenState = _screenState.asStateFlow()

    private var mangaId: String? = null

    suspend fun loadMangaDetails(mangaId: String) {
        this.mangaId = mangaId
        runCatching { getDetailMangaUseCase(mangaId) }
            .onSuccess { _screenState.value = DetailScreenState.Success(it) }
            .onFailure { DetailScreenState.Error(it.message.orEmpty()) }
    }

    fun updateRequest() {
        viewModelScope.launch {
            loadMangaDetails(mangaId.orEmpty())
        }
    }

    fun saveChapters(chapterId: List<String>) {
        saveChaptersUseCase(chapterId)
    }
}