package com.example.newsprojectj200.data.cache.db

import androidx.room.*
import com.example.newsprojectj200.data.cache.models.ArticleStorage
import javax.inject.Inject

@Database(entities = [ArticleStorage::class], version = 1)
@TypeConverters(SourceConverter::class)
abstract class AllNewsDatabase  : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {
        const val DATABASE_NAME = "AllNewsDatabase"
    }
}