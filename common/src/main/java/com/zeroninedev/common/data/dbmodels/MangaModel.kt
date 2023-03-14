package com.zeroninedev.common.data.dbmodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "manga")
data class MangaModel(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "status") val status: String
)