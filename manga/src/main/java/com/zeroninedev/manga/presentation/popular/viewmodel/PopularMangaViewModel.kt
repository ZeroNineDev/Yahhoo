package com.zeroninedev.manga.presentation.popular.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeroninedev.common.constants.Constants
import com.zeroninedev.common.domain.models.UpdatedManga
import com.zeroninedev.manga.domain.usecase.GetPopularMangaUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class PopularMangaViewModel @Inject constructor(
    private val getPopularMangaUseCase: GetPopularMangaUseCase
) : ViewModel() {

    private val _screenState = MutableStateFlow<List<UpdatedManga>>(listOf())
    val screenState = _screenState.asStateFlow()

    init {
        loadMangas()
    }

    private fun loadMangas() {
        viewModelScope.launch {
            runCatching { getPopularMangaUseCase() }
                .onSuccess { _screenState.value = it }
                .onFailure { Log.d(Constants.ERROR_LOG, it.message.toString()) }
        }
    }
}