package com.zeroninedev.manga.domain.usecase

import com.zeroninedev.manga.domain.StateRepository
import javax.inject.Inject

internal class GetAllChaptersUseCase @Inject constructor(private val repository: StateRepository) {


    operator fun invoke(): List<Pair<String, String>>  = repository.chapters
}