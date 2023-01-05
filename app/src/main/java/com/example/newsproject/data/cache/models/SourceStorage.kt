package com.example.newsproject.data.cache.models

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

class SourceStorage(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "source_id") val sourceId: String?= String(),
    @ColumnInfo(name = "source_name") val name: String? = String()
)