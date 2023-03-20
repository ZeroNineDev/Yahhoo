package com.zeroninedev.manga.presentation.search.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.zeroninedev.common.base.BaseViewModel
import com.zeroninedev.common.constants.Constants
import com.zeroninedev.manga.domain.usecase.GetSearchedMangaUseCase
import com.zeroninedev.manga.presentation.search.screen.SearchScreenState
import com.zeroninedev.manga.presentation.search.viewmodel.SearchMangaIntent.ClearResponse
import com.zeroninedev.manga.presentation.search.viewmodel.SearchMangaIntent.Typing
import com.zeroninedev.manga.presentation.search.viewmodel.SearchMangaIntent.UpdateResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
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
) : BaseViewModel<SearchScreenState>(SearchScreenState.Loading()) {

    private var debounceJob: Job? = null

    private var query: String? = null

    fun processIntent(intent: SearchMangaIntent) = when(intent) {
        is Typing -> launchSearch(intent.query)
        ClearResponse -> clearQuery()
        UpdateResponse -> relaunch()
    }

    /**
     * Start search by query
     *
     * @param query text for search
     */
    private fun launchSearch(query: String) {
        _screenState.value = SearchScreenState.Loading(query)
        this.query = query
        if(query.isEmpty()) clearQuery()
        else runQuery(query)
    }

    /**
     * Reload info about manga when error
     */
    private fun relaunch() {
        runQuery(query.orEmpty())
    }

    /**
     * Clear query for search
     */
    private fun clearQuery() {
        _screenState.value = SearchScreenState.Empty()
    }

    private fun runQuery(query: String) {
        debounceJob?.cancel()
        debounceJob = viewModelScope.launch {
            delay(TIME_DEBOUNCE_JOB)
            try { _screenState.value = SearchScreenState.Success(query, getSearchedMangaUseCase(query)) }
            catch (e: CancellationException){ Log.d(Constants.ERROR_LOG, e.message.toString()) }
            catch (e: Exception) { SearchScreenState.Error(query, e.message.orEmpty()) }
        }
    }

    private companion object {

        const val TIME_DEBOUNCE_JOB = 1000L
    }
}