package com.zeroninedev.manga.presentation.mangachapter.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.zeroninedev.common.base.BaseViewModel
import com.zeroninedev.common.constants.Constants
import com.zeroninedev.common.settingsmodel.SwitchPages.SCROLL_TO_NEXT
import com.zeroninedev.common.settingsmodel.SwitchPages.SWIPE_TO_NEXT
import com.zeroninedev.common.settingsmodel.SwitchPages.TAP_TO_NEXT
import com.zeroninedev.manga.domain.setting.GetMangaSwitchSettingUseCase
import com.zeroninedev.manga.domain.usecase.GetMangaChapterUseCase
import com.zeroninedev.manga.domain.usecase.GetNextChapterUseCase
import com.zeroninedev.manga.domain.usecase.UpdateChapterInfoUseCase
import com.zeroninedev.manga.presentation.mangachapter.model.ChapterState.NEXT_PAGE
import com.zeroninedev.manga.presentation.mangachapter.model.ChapterState.PREV_PAGE
import com.zeroninedev.manga.presentation.mangachapter.screen.MangaScreenState
import com.zeroninedev.manga.presentation.mangachapter.screen.MangaScreenState.Loading
import com.zeroninedev.manga.presentation.mangachapter.viewmodel.MangaChapterIntent.HalfPagesScrolled
import com.zeroninedev.manga.presentation.mangachapter.viewmodel.MangaChapterIntent.LoadMangaChapters
import com.zeroninedev.manga.presentation.mangachapter.viewmodel.MangaChapterIntent.LoadNextChapter
import com.zeroninedev.manga.presentation.mangachapter.viewmodel.MangaChapterIntent.LoadNextPage
import com.zeroninedev.manga.presentation.mangachapter.viewmodel.MangaChapterIntent.LoadPreviousChapter
import com.zeroninedev.manga.presentation.mangachapter.viewmodel.MangaChapterIntent.LoadPreviousPage
import com.zeroninedev.manga.presentation.mangachapter.viewmodel.MangaChapterIntent.SendErrorMessage
import com.zeroninedev.manga.presentation.mangachapter.viewmodel.MangaChapterIntent.SetNavigator
import com.zeroninedev.manga.presentation.mangachapter.viewmodel.MangaChapterIntent.UpdateResponse
import com.zeroninedev.navigation.actions.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class NewMangaChapterViewModel @Inject constructor(
    getMangaSwitchSettingUseCase: GetMangaSwitchSettingUseCase,
    private val getMangaChapterUseCase: GetMangaChapterUseCase,
    private val updateChapterInfoUseCase: UpdateChapterInfoUseCase,
    private val getNextChapterUseCase: GetNextChapterUseCase
) : BaseViewModel<MangaScreenState>(Loading) {

    private var navigator: Navigator? = null

    private var currentPages: List<String>? = null
    private var currentPage: Int = START_PAGE

    private val mangaSwitchType = getMangaSwitchSettingUseCase()

    private var nextChapterData: Triple<String, String, List<String>>? = null

    private var currentMangaId: String = ""
    private var currentChapterId: String = ""
    private var currentChapterName: String = ""

    fun processIntent(intent: MangaChapterIntent): Unit = when (intent) {
        HalfPagesScrolled -> startLoadNextChapter()
        UpdateResponse -> startLoadPage()
        LoadNextChapter -> loadNextChapter()
        LoadPreviousChapter -> loadPreviousChapter()
        LoadNextPage -> loadNextPage()
        LoadPreviousPage -> loadPreviousPage()
        is SendErrorMessage -> processError(intent.message)
        is LoadMangaChapters -> loadMangaChapters(intent.mangaId, intent.chapterId to intent.chapterName)
        is SetNavigator -> setNavigator(intent.navigator)
    }

    private fun startLoadNextChapter() {
        if (screenJob == null && nextChapterData == null) {
            runCatching { getNextChapterUseCase(currentChapterId to currentChapterName, NEXT_PAGE) }
                .onSuccess { startLoadPageJob(currentMangaId, it) }
        }
    }

    private fun loadPreviousChapter() {
        _screenState.value = Loading
        currentPage = START_PAGE
        runCatching { getNextChapterUseCase(currentChapterId to currentChapterName, PREV_PAGE) }
            .onSuccess { loadMangaChapters(currentMangaId, it) }
            .onFailure { backStack() }
    }

    private fun loadNextChapter() {
        _screenState.value = Loading
        currentPage = START_PAGE
        viewModelScope.launch {
            updateChapterInfoUseCase(currentChapterId, currentMangaId)
            delay(100)
            when {
                screenJob != null -> processScreenJob()
                nextChapterData?.second.isNullOrEmpty().not() -> {
                    setNextScreenInfo()
                    processMangaType(nextChapterData?.third)
                    closeJob()
                }
                else -> {
                    runCatching { getNextChapterUseCase(currentChapterId to currentChapterName, NEXT_PAGE) }
                        .onSuccess { loadMangaChapters(currentMangaId, it) }
                    closeJob()
                }
            }
        }
    }

    private fun closeJob() {
        screenJob?.cancel()
        screenJob = null
        nextChapterData = null
    }

    private fun setNextScreenInfo() {
        currentChapterId = nextChapterData?.first.orEmpty()
        currentChapterName = nextChapterData?.second.orEmpty()
    }

    private fun processScreenJob() {
        if (screenJob?.isActive == true) {
            screenJob?.invokeOnCompletion {
                nextChapterData?.let {
                    setNextScreenInfo()
                    processMangaType(it.third)
                } ?: processError()
                closeJob()
            }
        }
    }

    private fun loadPreviousPage() {
        if (currentPage < 1) return loadPreviousChapter()
        currentPage--
        currentPages?.let { data -> processMangaType(data) } ?: processError()
    }

    private fun loadNextPage() {
        currentPages?.let { data ->
            when {
                currentPage > (data.lastIndex - 1) -> return loadNextChapter()
                currentPage > (data.lastIndex / 2) -> startLoadNextChapter()
            }
            currentPage++
            processMangaType(data)
        } ?: processError()
    }

    private fun loadMangaChapters(mangaId: String, chapter: Pair<String, String>) {
        currentMangaId = mangaId
        currentChapterId = chapter.first
        currentChapterName = chapter.second
        closeJob()
        startLoadPage()
    }

    private fun startLoadPage() {
        viewModelScope.launch {
            runCatching { getMangaChapterUseCase(currentMangaId, currentChapterId) }
                .onSuccess { processMangaType(it) }
                .onFailure { processError(it.message) }
        }
    }

    private fun processMangaType(data: List<String>?) {
        data?.let {
            currentPages = data
            _screenState.value = when (mangaSwitchType) {
                TAP_TO_NEXT -> MangaScreenState.TapSuccess(
                    data = data[currentPage],
                    currentPage = currentPage,
                    maxPage = data.size,
                    chapterName = currentChapterName
                )
                SWIPE_TO_NEXT -> MangaScreenState.SwitchSuccess(
                    data = data[currentPage],
                    currentPage = currentPage,
                    maxPage = data.size,
                    chapterName = currentChapterName
                )
                SCROLL_TO_NEXT -> MangaScreenState.ScrollSuccess(data, currentChapterName)
            }
        } ?: processError()
    }

    private fun processError(message: String? = null) {
        Log.d(Constants.LOG, message.orEmpty())
        _screenState.value = MangaScreenState.Error(message ?: "Sheet happens")
    }

    private fun startLoadPageJob(mangaId: String, chapterId: Pair<String, String>) {
        screenJob = viewModelScope.launch {
            runCatching { getMangaChapterUseCase(mangaId, chapterId.first) }
                .onSuccess { nextChapterData = Triple(chapterId.first, chapterId.second, it) }
            screenJob = null
        }
    }

    private fun setNavigator(navigator: Navigator) {
        this.navigator = navigator
    }

    private fun backStack() {
        navigator?.goBackStack()
    }

    private companion object {

        const val START_PAGE = 0
    }
}