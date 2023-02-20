package com.zeroninedev.manga.domain

internal interface StateRepository {

    fun saveChapters(chapters: List<String>)

    fun getChapters(): List<String>
}