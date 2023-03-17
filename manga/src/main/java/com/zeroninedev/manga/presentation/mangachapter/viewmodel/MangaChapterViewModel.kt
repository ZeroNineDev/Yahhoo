package com.zeroninedev.manga.presentation.mangachapter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeroninedev.manga.domain.setting.GetMangaSwitchSettingUseCase
import com.zeroninedev.manga.domain.usecase.GetMangaChapterUseCase
import com.zeroninedev.manga.domain.usecase.GetNextChapterUseCase
import com.zeroninedev.manga.domain.usecase.UpdateChapterInfoUseCase
import com.zeroninedev.manga.presentation.mangachapter.model.ChapterState.NEXT_PAGE
import com.zeroninedev.manga.presentation.mangachapter.model.ChapterState.PREV_PAGE
import com.zeroninedev.manga.presentation.mangachapter.screen.MangaScreenState
import com.zeroninedev.navigation.actions.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View Model for reader chapter screen
 *
 * @property getMangaChapterUseCase use case for load pages of chapter
 * @property getNextChapterUseCase use case for get next page
 * @property updateChapterInfoUseCase use case for get next page
 * @property getMangaSwitchSettingUseCase use case for load swich manga setting
 */
@HiltViewModel
internal class MangaChapterViewModel @Inject constructor(
    private val getMangaChapterUseCase: GetMangaChapterUseCase,
    private val updateChapterInfoUseCase: UpdateChapterInfoUseCase,
    private val getMangaSwitchSettingUseCase: GetMangaSwitchSettingUseCase,
    private val getNextChapterUseCase: GetNextChapterUseCase
) : ViewModel() {

    private var navigator: Navigator? = null

    private val _screenState = MutableStateFlow<MangaScreenState>(MangaScreenState.Loading)
    val screenState = _screenState.asStateFlow()

    private var nextChapterState: MangaScreenState? = null
    private var loadingPageJob: Job? = null

    private var chapterId: String? = null
    private var mangaId: String? = null

    /**
     * Load pages of chapter
     *
     * @param mangaId manga id
     * @param chapterId chapter Id
     */
    suspend fun loadMangaChapter(mangaId: String, chapterId: String) {
        _screenState.value = MangaScreenState.Loading
        this.chapterId = chapterId
        this.mangaId = mangaId

        if (loadingPageJob != null || nextChapterState != null) {
            delay(100)
            if (loadingPageJob?.isActive == true) {
                loadingPageJob?.invokeOnCompletion {
                    setScreen()
                }
            }else {
                setScreen()
            }
        } else {
            runCatching { getMangaChapterUseCase(mangaId, chapterId) }
                .onSuccess { _screenState.value = MangaScreenState.Success(it, getMangaSwitchSettingUseCase()) }
                .onFailure { _screenState.value = MangaScreenState.Error(it.message.orEmpty()) }
        }
    }

    /**
     * Reload info about pages when error
     */
    fun updateRequest() {
        viewModelScope.launch {
            loadMangaChapter(mangaId.orEmpty(), chapterId.orEmpty())
        }
    }

    /**
     * Set navigator for back stack when end of manga chapters
     *
     * @param navigator navigator
     */
    fun setNavigator(navigator: Navigator) {
        this.navigator = navigator
    }

    /**
     * Update screen state when image not loaded
     *
     * @param text error text
     */
    fun sendImageError(text: String?) {
        _screenState.value = MangaScreenState.Error(text.orEmpty())
    }

    /**
     * Load next chapter if consist
     */
    fun loadNextChapter() {
        viewModelScope.launch {
            try {
                updateChapterInfoUseCase(chapterId.orEmpty(), mangaId.orEmpty())
                val nextChapterId = getNextChapterUseCase(chapterId.orEmpty(), NEXT_PAGE)
                loadMangaChapter(mangaId.orEmpty(), nextChapterId)
            } catch (e: Exception) {
                navigator?.goBackStack()
            }
        }
    }

    /**
     * Start preloading next page if half part ended
     */
    fun preloadNext() {
        if (loadingPageJob == null && nextChapterState == null) {
            try {
                val nextChapterId = getNextChapterUseCase(chapterId.orEmpty(), NEXT_PAGE)
                startLoadPageJob(mangaId.orEmpty(), nextChapterId)
            } catch (_: Exception) { }
        }
    }

    /**
     * Load prev chapter if consist
     *
     */
    fun loadPrevChapter() {
        viewModelScope.launch {
            try {
                val prevChapterId = getNextChapterUseCase(chapterId.orEmpty(), PREV_PAGE)
                loadMangaChapter(mangaId.orEmpty(), prevChapterId)
            } catch (e: Exception) {
                navigator?.goBackStack()
            }
        }
    }

    private fun startLoadPageJob(mangaId: String, chapterId: String) {
        loadingPageJob = viewModelScope.launch {
            runCatching { getMangaChapterUseCase(mangaId, chapterId) }
                .onSuccess { nextChapterState = MangaScreenState.Success(it, getMangaSwitchSettingUseCase()) }
                .onFailure { nextChapterState = MangaScreenState.Error(it.message.orEmpty()) }
            loadingPageJob = null
        }
    }

    private fun setScreen() {
        _screenState.value = nextChapterState ?: MangaScreenState.Error("Shit happens")
        nextChapterState = null
    }
}