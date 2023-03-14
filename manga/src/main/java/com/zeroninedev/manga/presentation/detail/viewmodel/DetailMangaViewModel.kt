package com.zeroninedev.manga.presentation.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeroninedev.common.domain.models.Manga
import com.zeroninedev.common.domain.models.MangaReadStatus
import com.zeroninedev.manga.domain.usecase.GetDetailMangaUseCase
import com.zeroninedev.manga.domain.usecase.SaveChaptersUseCase
import com.zeroninedev.manga.domain.usecase.UpdateMangaInfoUseCase
import com.zeroninedev.manga.presentation.detail.screen.DetailScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View Model for detail screen
 *
 * @property getDetailMangaUseCase use case for load detail about manga
 * @property updateMangaUseCase use case for update info in db about manga
 * @property saveChaptersUseCase use case for save all chapters in current manga
 */
internal class DetailMangaViewModel @Inject constructor(
    private val getDetailMangaUseCase: GetDetailMangaUseCase,
    private val updateMangaUseCase: UpdateMangaInfoUseCase,
    private val saveChaptersUseCase: SaveChaptersUseCase
) : ViewModel() {

    private val _screenState = MutableStateFlow<DetailScreenState>(DetailScreenState.Loading)
    val screenState = _screenState.asStateFlow()

    private val _bottomSheet = MutableStateFlow(false)
    val bottomSheet = _bottomSheet.asStateFlow()


    private var manga: Manga? = null
    private var mangaId: String? = null

    /**
     * Load detailed info about manga
     *
     * @param mangaId manga id
     */
    suspend fun loadMangaDetails(mangaId: String) {
        this.mangaId = mangaId
        runCatching { getDetailMangaUseCase(mangaId) }
            .onSuccess {
                _screenState.value = DetailScreenState.Success(it)
                manga = it
            }
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

    fun showMangaStatusBottomSheet() {
        _bottomSheet.value = true
    }

    fun hideMangaStatusBottomSheet() {
        _bottomSheet.value = false
    }

    /**
     * Update info about manga in db
     *
     * @param mangaStatus manga
     */
    fun saveUpdatedInfo(mangaStatus: MangaReadStatus) {
        viewModelScope.launch {
            manga?.copy(mangaStatus = mangaStatus)?.let { updatedManga ->
                updateMangaUseCase(updatedManga)
                _screenState.value = DetailScreenState.Success(updatedManga)
            }
            hideMangaStatusBottomSheet()
        }
    }
}