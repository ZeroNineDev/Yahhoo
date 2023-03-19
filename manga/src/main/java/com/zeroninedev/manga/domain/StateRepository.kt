package com.zeroninedev.manga.domain

/**
 * Interface for storage of process state
 *
 */
internal interface StateRepository {

    /**
     * Chapters list of current manga
     */
    var chapters: List<Pair<String, String>>
}