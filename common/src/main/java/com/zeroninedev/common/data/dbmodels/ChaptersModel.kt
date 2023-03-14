package com.zeroninedev.common.data.dbmodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chapters")
data class ChaptersModel(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "manga_key") val mangaKey: String,
    @ColumnInfo(name = "id_no_manga_name") val idWithoutMangaName: String,
    @ColumnInfo(name = "is_read") val wasRead: Boolean
)