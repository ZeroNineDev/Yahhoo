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

/**
 * View Model for detail screen
 *
 * @property getDetailMangaUseCase use case for load detail about manga
 * @property saveChaptersUseCase use case for save all chapters in current manga
 */
internal class DetailMangaViewModel @Inject constructor(
    private val getDetailMangaUseCase: GetDetailMangaUseCase,
    private val saveChaptersUseCase: SaveChaptersUseCase
) : ViewModel() {

    private val _screenState = MutableStateFlow<DetailScreenState>(DetailScreenState.Loading)
    val screenState = _screenState.asStateFlow()

    private var mangaId: String? = null

    /**
     * Load detailed info about manga
     *
     * @param mangaId manga id
     */
    suspend fun loadMangaDetails(mangaId: String) {
        this.mangaId = mangaId
        runCatching { getDetailMangaUseCase(mangaId) }
            .onSuccess { _screenState.value = DetailScreenState.Success(it) }
            .onFailure { DetailScreenState.Error(it.message.orEmpty()) }
    }

    /**
     * Reload info about manga when error
     */
    fun updateRequest() {
        viewModelScope.launch {
            loadMangaDetails(mangaId.orEmpty())
        }
    }

    /**
     * Save all chapters in this manga
     *
     * @param chapterId list of chapters
     */
    fun saveChapters(chapterId: List<String>) {
        saveChaptersUseCase(chapterId)
    }
}