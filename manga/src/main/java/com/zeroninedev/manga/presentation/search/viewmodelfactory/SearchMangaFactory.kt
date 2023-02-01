package com.zeroninedev.manga.presentation.search.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zeroninedev.manga.domain.usecase.GetSearchedMangaUseCase
import com.zeroninedev.manga.presentation.search.viewmodel.SearchMangaViewModel
import javax.inject.Inject

internal class SearchMangaFactory @Inject constructor(
    private val getSearchedMangaUseCase: GetSearchedMangaUseCase
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        SearchMangaViewModel(getSearchedMangaUseCase) as T
}