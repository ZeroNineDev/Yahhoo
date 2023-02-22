package com.zeroninedev.manga.domain.usecase

import com.zeroninedev.common.domain.NetworkRepository
import com.zeroninedev.common.domain.models.UpdatedManga
import javax.inject.Inject

/**
 * Use case for load searched manga list
 *
 * @property repository interface for request in manga service
 */
internal class GetSearchedMangaUseCase @Inject constructor(private val repository: NetworkRepository) {

    /**
     * @param query query to find manga with coincidence
     * @return list of mangas with coincidence
     */
    suspend operator fun invoke(query: String): List<UpdatedManga> = repository.searchMangaByName(query)
}