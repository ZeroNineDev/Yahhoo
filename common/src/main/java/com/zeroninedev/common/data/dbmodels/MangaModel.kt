package com.zeroninedev.common.data.dbmodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zeroninedev.common.domain.models.MangaReadStatus
import com.zeroninedev.common.domain.models.UpdatedManga

@Entity(tableName = "manga")
data class MangaModel(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "status") val status: String
)

internal fun MangaModel.toDomain() = UpdatedManga(
    id = id,
    title = title,
    imageUrl = imageUrl,
    status = MangaReadStatus.valueOf(status)
)