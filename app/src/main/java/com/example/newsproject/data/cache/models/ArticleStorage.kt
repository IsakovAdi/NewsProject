package com.example.newsprojectj200.data.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsprojectj200.data.cache.models.ArticleStorage.Companion.ARTICLES_TABLE
import java.util.*

@Entity(tableName = ARTICLES_TABLE)
data class ArticleStorage(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = Random().nextInt(),
    @ColumnInfo(name = "resourceType")
    val resourceType: ResourceType,
    @ColumnInfo(name = "url")
    val url: String? = String(),
    @ColumnInfo(name = "author")
    val author: String? = String(),
    @ColumnInfo(name = "title")
    val title: String? = String(),
    @ColumnInfo(name = "description")
    val description: String? = String(),
    @ColumnInfo(name = "publishedAt")
    val publishedAt: String? = String(),
    @ColumnInfo(name = "urlToImage")
    val urlToImage: String? = String(),
    @ColumnInfo(name = "content")
    val content: String? = String(),
    @ColumnInfo(name = "source")
    val source: SourceStorage? = SourceStorage(String(), String()),
) {
    companion object {
        const val ARTICLES_TABLE = "articles_table"
    }
}

enum class ResourceType {
    ALL_NEWS,
    TOP_NEWS,
    SAVED
}
