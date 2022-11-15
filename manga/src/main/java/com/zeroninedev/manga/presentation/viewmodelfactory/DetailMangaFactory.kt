package com.zeroninedev.manga.presentation.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zeroninedev.manga.domain.usecase.GetDetailMangaUseCase
import com.zeroninedev.manga.presentation.viewmodel.DetailMangaViewModel
import javax.inject.Inject

internal class DetailMangaFactory @Inject constructor(
    private val getDetailMangaUseCase: GetDetailMangaUseCase
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        DetailMangaViewModel(getDetailMangaUseCase) as T
}