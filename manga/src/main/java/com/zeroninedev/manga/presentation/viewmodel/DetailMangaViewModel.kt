package com.zeroninedev.manga.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeroninedev.common.constants.Constants
import com.zeroninedev.manga.domain.models.Manga
import com.zeroninedev.manga.domain.usecase.GetDetailMangaUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class DetailMangaViewModel @Inject constructor(
    private val getDetailMangaUseCase: GetDetailMangaUseCase
) : ViewModel() {

    private val _screenState = MutableStateFlow<Manga?>(null)
    val screenState = _screenState.asStateFlow()

    fun loadMangaDetails(mangaId: String) {
        viewModelScope.launch {
            runCatching { getDetailMangaUseCase(mangaId) }
                .onSuccess { _screenState.value = it }
                .onFailure { Log.d(Constants.ERROR_LOG, it.message.toString()) }
        }
    }
}