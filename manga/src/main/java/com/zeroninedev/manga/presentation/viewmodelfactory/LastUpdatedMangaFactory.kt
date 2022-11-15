package com.zeroninedev.manga.presentation.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zeroninedev.manga.domain.usecase.GetLastUpdatedMangaUseCase
import com.zeroninedev.manga.presentation.viewmodel.LastUpdatedMangaViewModel
import javax.inject.Inject

internal class LastUpdatedMangaFactory @Inject constructor(
    private val getLastUpdatedMangaUseCase: GetLastUpdatedMangaUseCase
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        LastUpdatedMangaViewModel(getLastUpdatedMangaUseCase) as T
}