package com.zeroninedev.manga.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeroninedev.manga.domain.NetworkRepository
import com.zeroninedev.manga.domain.models.UpdatedManga
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import javax.inject.Inject

internal class LastUpdatedMangaViewModel @Inject constructor(
    private val networkRepository: NetworkRepository
) : ViewModel() {

    private val _screenState = MutableStateFlow<List<UpdatedManga>>(listOf())
    val screenState = _screenState.asStateFlow()

    init {
        loadMangas()
    }

    private fun loadMangas() {
        viewModelScope.launch {
            runCatching { networkRepository.lastUpdatedMangas() }
                .onSuccess { _screenState.value = it }
                .onFailure { Log.d("HERE", it.message.toString()) }
        }
    }
}