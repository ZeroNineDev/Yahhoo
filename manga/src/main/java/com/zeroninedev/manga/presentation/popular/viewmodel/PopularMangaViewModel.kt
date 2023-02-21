package com.zeroninedev.manga.presentation.popular.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.zeroninedev.common.constants.Constants
import com.zeroninedev.common.domain.models.UpdatedManga
import com.zeroninedev.manga.domain.usecase.GetPopularMangaUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class PopularMangaViewModel @Inject constructor(
    private val getPopularMangaUseCase: GetPopularMangaUseCase
) : ViewModel() {

    private lateinit var _screenState: Flow<PagingData<UpdatedManga>>
    val screenState: Flow<PagingData<UpdatedManga>>
        get() = _screenState

    init {
        loadMangas()
    }

    private fun loadMangas() {
        viewModelScope.launch {
            runCatching { getPopularMangaUseCase().cachedIn(viewModelScope) }
                .onSuccess { _screenState = it }
                .onFailure { Log.d(Constants.ERROR_LOG, it.message.toString()) }
        }
    }
}