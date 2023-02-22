package com.zeroninedev.manga.presentation.mangachapter.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zeroninedev.manga.domain.usecase.GetMangaChapterUseCase
import com.zeroninedev.manga.domain.usecase.GetNextChapterUseCase
import com.zeroninedev.manga.presentation.mangachapter.viewmodel.MangaChapterViewModel
import javax.inject.Inject

internal class MangaChapterFactory @Inject constructor(
    private val getMangaChapterUseCase: GetMangaChapterUseCase,
    private val getNextChapterUseCase: GetNextChapterUseCase
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MangaChapterViewModel(getMangaChapterUseCase, getNextChapterUseCase) as T
}