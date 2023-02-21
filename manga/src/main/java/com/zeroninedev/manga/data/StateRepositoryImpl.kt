package com.zeroninedev.manga.data

import com.zeroninedev.manga.domain.StateRepository
import javax.inject.Inject

internal class StateRepositoryImpl @Inject constructor(): StateRepository {

    private var chapters: List<String> = listOf()

    override fun saveChapters(chapters: List<String>) {
        this.chapters = chapters
    }

    override fun getChapters(): List<String> = chapters
}