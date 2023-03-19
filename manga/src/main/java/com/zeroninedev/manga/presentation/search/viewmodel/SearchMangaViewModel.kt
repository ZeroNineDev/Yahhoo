package com.zeroninedev.manga.presentation.search.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeroninedev.common.constants.Constants
import com.zeroninedev.manga.domain.usecase.GetSearchedMangaUseCase
import com.zeroninedev.manga.presentation.search.screen.SearchScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View Model for search manga screen
 *
 * @property getSearchedMangaUseCase use case for search manga
 */
@HiltViewModel
internal class SearchMangaViewModel @Inject constructor(
    private val getSearchedMangaUseCase: GetSearchedMangaUseCase
) : ViewModel() {

    private val _screenState = MutableStateFlow<SearchScreenState>(SearchScreenState.Empty)
    val screenState = _screenState.asStateFlow()

    private var debounceJob: Job? = null

    private var query: String? = null

    /**
     * Start search by query
     *
     * @param query text for search
     */
    fun launchSearch(query: String) {
        this.query = query
        if(query.isEmpty()) clearQuery()
        else runQuery(query)
    }

    /**
     * Reload info about manga when error
     */
    fun relaunch() {
        runQuery(query.orEmpty())
    }

    /**
     * Clear query for search
     */
    fun clearQuery() {
        _screenState.value = SearchScreenState.Empty
    }

    private fun runQuery(query: String) {
        debounceJob?.cancel()
        debounceJob = viewModelScope.launch {
            delay(TIME_DEBOUNCE_JOB)
            try { _screenState.value = SearchScreenState.Success(getSearchedMangaUseCase(query)) }
            catch (e: CancellationException){ Log.d(Constants.ERROR_LOG, e.message.toString()) }
            catch (e: Exception) { SearchScreenState.Error(e.message.orEmpty()) }
        }
    }

    private companion object {

        const val TIME_DEBOUNCE_JOB = 1000L
    }
}