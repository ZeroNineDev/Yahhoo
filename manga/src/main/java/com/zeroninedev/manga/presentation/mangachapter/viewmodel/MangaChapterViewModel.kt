package com.zeroninedev.manga.presentation.mangachapter.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.zeroninedev.common.constants.Constants
import com.zeroninedev.manga.domain.usecase.GetMangaChapterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

internal class MangaChapterViewModel @Inject constructor(
    private val getMangaChapterUseCase: GetMangaChapterUseCase
) : ViewModel() {

    private val _screenState = MutableStateFlow<List<String>?>(null)
    val screenState = _screenState.asStateFlow()

    suspend fun loadMangaChapter(mangaId: String, chapterId: String) {
        runCatching { getMangaChapterUseCase(mangaId, chapterId) }
            .onSuccess { _screenState.value = it }
            .onFailure { Log.d(Constants.ERROR_LOG, it.message.toString()) }
    }
}