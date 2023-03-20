package com.zeroninedev.manga.presentation.detail.viewmodel

import androidx.lifecycle.viewModelScope
import com.zeroninedev.common.base.BaseViewModel
import com.zeroninedev.common.domain.models.Manga
import com.zeroninedev.common.domain.models.MangaReadStatus
import com.zeroninedev.common.domain.models.MangaReadStatus.UNKNOWN
import com.zeroninedev.manga.domain.usecase.GetDetailMangaUseCase
import com.zeroninedev.manga.domain.usecase.SaveChaptersUseCase
import com.zeroninedev.manga.domain.usecase.UpdateChapterInfoUseCase
import com.zeroninedev.manga.domain.usecase.UpdateMangaInfoUseCase
import com.zeroninedev.manga.presentation.detail.screen.BottomSheetState
import com.zeroninedev.manga.presentation.detail.screen.DetailScreenState
import com.zeroninedev.manga.presentation.detail.viewmodel.DetailBottomSheetIntent.DismissBottomSheet
import com.zeroninedev.manga.presentation.detail.viewmodel.DetailBottomSheetIntent.ProcessMangaChapter
import com.zeroninedev.manga.presentation.detail.viewmodel.DetailBottomSheetIntent.SelectMangaStatus
import com.zeroninedev.manga.presentation.detail.viewmodel.DetailMangaIntent.LoadMangaInfo
import com.zeroninedev.manga.presentation.detail.viewmodel.DetailMangaIntent.SaveSelectedManga
import com.zeroninedev.manga.presentation.detail.viewmodel.DetailMangaIntent.ShowChapterDetailInfo
import com.zeroninedev.manga.presentation.detail.viewmodel.DetailMangaIntent.ShowMangaReadStatus
import com.zeroninedev.manga.presentation.detail.viewmodel.DetailMangaIntent.UpdateRequest
import dagger.hilt.android.lifecycle.HiltViewModel
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
@HiltViewModel
internal class DetailMangaViewModel @Inject constructor(
    private val getDetailMangaUseCase: GetDetailMangaUseCase,
    private val updateMangaUseCase: UpdateMangaInfoUseCase,
    private val updateChapterInfoUseCase: UpdateChapterInfoUseCase,
    private val saveChaptersUseCase: SaveChaptersUseCase
) : BaseViewModel<DetailScreenState>(DetailScreenState.Loading) {

    private val _bottomSheet = MutableStateFlow<BottomSheetState>(BottomSheetState.None)
    val bottomSheet = _bottomSheet.asStateFlow()

    private var manga: Manga? = null
    private var mangaId: String? = null

    fun processIntent(intent: DetailMangaIntent) = when (intent) {
        is LoadMangaInfo -> loadMangaDetails(intent.mangaId)
        is ShowChapterDetailInfo -> showWasReadStateBottomSheet(intent.chapterId)
        SaveSelectedManga -> saveChapters()
        ShowMangaReadStatus -> showMangaStatusBottomSheet()
        UpdateRequest -> updateRequest()
    }

    fun processBottomSheetIntent(intent: DetailBottomSheetIntent) = when (intent) {
        is SelectMangaStatus -> saveUpdatedInfo(intent.mangaReadStatus)
        is ProcessMangaChapter -> saveUpdatedChapter(intent.chapterId, intent.state)
        DismissBottomSheet -> Unit
    }.also { hideBottomSheet() }

    /**
     * Load detailed info about manga
     *
     * @param mangaId manga id
     */
    private fun loadMangaDetails(mangaId: String) {
        this.mangaId = mangaId
        viewModelScope.launch {
            runCatching { getDetailMangaUseCase(mangaId) }
                .onSuccess {
                    _screenState.value = DetailScreenState.Success(it)
                    manga = it
                }
                .onFailure { DetailScreenState.Error(it.message.orEmpty()) }
        }
    }

    /**
     * Reload info about manga when error
     */
    private fun updateRequest() {
        loadMangaDetails(mangaId.orEmpty())
    }

    private fun saveChapters() {
        manga?.let { manga ->
            saveChaptersUseCase(manga.chapters.map { it.id.orEmpty() to it.title.orEmpty() })
        }
    }

    private fun showMangaStatusBottomSheet() {
        _bottomSheet.value = BottomSheetState.ReadStatus(manga?.mangaStatus ?: UNKNOWN)
    }

    private fun showWasReadStateBottomSheet(chapterId: String) {
        _bottomSheet.value = BottomSheetState.WasReadState(chapterId)
    }

    private fun hideBottomSheet() {
        _bottomSheet.value = BottomSheetState.None
    }

    /**
     * Update info about manga in db
     *
     * @param chapterId manga
     */
    private fun saveUpdatedChapter(chapterId: String, state: Boolean) {
        viewModelScope.launch {
            manga?.let { innerManga ->
                updateChapterInfoUseCase(mangaId = mangaId.orEmpty(), chapterId = chapterId, wasRead = state)
                val chapters = innerManga.chapters.map { if (it.id == chapterId) it.copy(wasRead = state) else it }
                innerManga.copy(chapters = chapters).let { updatedManga ->
                    _screenState.value = DetailScreenState.Success(updatedManga)
                    manga = updatedManga
                }
            }
        }
    }

    /**
     * Update info about manga in db
     *
     * @param mangaStatus manga
     */
    private fun saveUpdatedInfo(mangaStatus: MangaReadStatus) {
        viewModelScope.launch {
            manga?.copy(mangaStatus = mangaStatus)?.let { updatedManga ->
                updateMangaUseCase(updatedManga)
                _screenState.value = DetailScreenState.Success(updatedManga)
                manga = updatedManga
            }
        }
    }
}