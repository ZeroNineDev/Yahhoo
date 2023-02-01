package com.zeroninedev.manga.presentation.popular.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zeroninedev.manga.domain.usecase.GetPopularMangaUseCase
import com.zeroninedev.manga.presentation.popular.viewmodel.PopularMangaViewModel
import javax.inject.Inject

internal class PopularMangaFactory @Inject constructor(
    private val getPopularMangaUseCase: GetPopularMangaUseCase
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        PopularMangaViewModel(getPopularMangaUseCase) as T
}