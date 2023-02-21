package com.zeroninedev.manga.domain.usecase

import com.zeroninedev.manga.domain.StateRepository
import javax.inject.Inject

internal class SaveChaptersUseCase @Inject constructor(private val repository: StateRepository) {

     operator fun invoke(chapters: List<String>) = repository.saveChapters(chapters)
}