package com.zeroninedev.manga.domain.usecase

import com.zeroninedev.manga.domain.StateRepository
import javax.inject.Inject

/**
 * Use case for save all chapters in current manga
 *
 * @property repository interface storage process information
 */
internal class SaveChaptersUseCase @Inject constructor(private val repository: StateRepository) {

     /**
      * @param chapters list of chapters
      */
     operator fun invoke(chapters: List<String>) {
          repository.chapters = chapters
     }
}