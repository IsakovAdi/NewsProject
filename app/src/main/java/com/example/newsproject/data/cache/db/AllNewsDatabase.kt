package com.example.newsproject.data.cache.db

import androidx.room.*
import com.example.newsproject.data.cache.models.ArticleStorage

@Database(entities = [ArticleStorage::class], version = 1)
@TypeConverters(SourceConverter::class)
abstract class AllNewsDatabase  : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {
        const val DATABASE_NAME = "AllNewsDatabase"
    }
}