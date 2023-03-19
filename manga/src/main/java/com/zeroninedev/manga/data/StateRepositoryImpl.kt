package com.zeroninedev.manga.data

import com.zeroninedev.manga.domain.StateRepository
import javax.inject.Inject

/**
 * Repository for storage of process state
 * Realization of [StateRepository]
 *
 */
internal class StateRepositoryImpl @Inject constructor() : StateRepository {

    override var chapters: List<Pair<String, String>> = listOf()
}