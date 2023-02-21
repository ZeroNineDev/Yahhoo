package com.zeroninedev.manga.presentation.mangachapter.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeroninedev.common.constants.Constants
import com.zeroninedev.manga.domain.usecase.GetMangaChapterUseCase
import com.zeroninedev.manga.domain.usecase.GetNextChapterUseCase
import com.zeroninedev.manga.presentation.mangachapter.model.ChapterState.NEXT_PAGE
import com.zeroninedev.manga.presentation.mangachapter.model.ChapterState.PREV_PAGE
import com.zeroninedev.manga.presentation.mangachapter.screen.MangaScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class MangaChapterViewModel @Inject constructor(
    private val getMangaChapterUseCase: GetMangaChapterUseCase,
    private val getNextChapterUseCase: GetNextChapterUseCase
) : ViewModel() {

    private val _screenState = MutableStateFlow<MangaScreenState>(MangaScreenState.Loading)
    val screenState = _screenState.asStateFlow()

    private var chapterId: String? = null
    private var mangaId: String? = null

    suspend fun loadMangaChapter(mangaId: String, chapterId: String) {
        _screenState.value = MangaScreenState.Loading
        this.chapterId = chapterId
        this.mangaId = mangaId
        runCatching { getMangaChapterUseCase(mangaId, chapterId) }
            .onSuccess { _screenState.value = MangaScreenState.Success(it) }
            .onFailure { _screenState.value = MangaScreenState.Error(it) }
    }

    fun loadNextChapter() {
        viewModelScope.launch {
            try {
                val nextChapterId = getNextChapterUseCase(chapterId.orEmpty(), NEXT_PAGE)
                loadMangaChapter(mangaId.orEmpty(), nextChapterId)
            } catch (e: Exception) {
                Log.d(Constants.ERROR_LOG, e.message.toString())
            }
        }
    }

    fun loadPrevChapter() {
        viewModelScope.launch {
            try {
                val prevChapterId = getNextChapterUseCase(chapterId.orEmpty(), PREV_PAGE)
                loadMangaChapter(mangaId.orEmpty(), prevChapterId)
            } catch (e: Exception) {
                Log.d(Constants.ERROR_LOG, e.message.toString())
            }
        }
    }
}