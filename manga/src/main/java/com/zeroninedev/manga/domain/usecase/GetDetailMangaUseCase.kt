package com.zeroninedev.manga.domain.usecase

import com.zeroninedev.manga.domain.NetworkRepository
import com.zeroninedev.manga.domain.models.Manga
import javax.inject.Inject

internal class GetDetailMangaUseCase @Inject constructor(private val repository: NetworkRepository) {

    suspend operator fun invoke(mangaId: String): Manga = repository.mangaDetail(mangaId)
}