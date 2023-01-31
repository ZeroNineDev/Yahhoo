package com.zeroninedev.manga.domain.usecase

import com.zeroninedev.common.domain.NetworkRepository
import com.zeroninedev.common.domain.models.UpdatedManga
import javax.inject.Inject

internal class GetLastUpdatedMangaUseCase @Inject constructor(private val repository: NetworkRepository) {

    suspend operator fun invoke(): List<UpdatedManga> = repository.lastUpdatedMangas()
}