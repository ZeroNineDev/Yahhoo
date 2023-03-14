package com.zeroninedev.manga.presentation.saved.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zeroninedev.manga.domain.usecase.GetSavedMangasUseCase
import com.zeroninedev.manga.presentation.saved.viewmodel.SavedMangaViewModel
import javax.inject.Inject

internal class SavedMangaFactory @Inject constructor(
    private val getSavedMangasUseCase: GetSavedMangasUseCase
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        SavedMangaViewModel(getSavedMangasUseCase) as T
}