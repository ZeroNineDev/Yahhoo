package com.zeroninedev.manga.presentation.search.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeroninedev.common.constants.Constants
import com.zeroninedev.common.domain.models.UpdatedManga
import com.zeroninedev.manga.domain.usecase.GetSearchedMangaUseCase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class SearchMangaViewModel @Inject constructor(
    private val getSearchedMangaUseCase: GetSearchedMangaUseCase
) : ViewModel() {

    private val _screenState = MutableStateFlow<List<UpdatedManga>>(listOf())
    val screenState = _screenState.asStateFlow()

    private var debounceJob: Job? = null

    fun launchSearch(query: String) {
        if(query.isEmpty()) clearQuery()
        else runQuery(query)
    }

    fun clearQuery() {
        _screenState.value = listOf()
    }

    private fun runQuery(query: String) {
        debounceJob?.cancel()
        debounceJob = viewModelScope.launch {
            delay(TIME_DEBOUNCE_JOB)
            try { _screenState.value = getSearchedMangaUseCase(query) }
            catch (e: CancellationException){ Log.d(Constants.ERROR_LOG, e.message.toString()) }
            catch (e: Exception) { Log.d(Constants.ERROR_LOG, e.message.toString()) }
        }
    }

    private companion object {

        const val TIME_DEBOUNCE_JOB = 500L
    }
}