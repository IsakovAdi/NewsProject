package com.example.newsprojectj200.data.cache.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.newsprojectj200.data.cache.models.ArticleStorage
import com.example.newsprojectj200.data.cache.models.ResourceType
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("select * from ARTICLES_TABLE where resourceType == :resourceType")
    fun fetchAllArticlesFromDatabaseObservable(resourceType: ResourceType): Flow<MutableList<ArticleStorage>>

    @Query("select * from ARTICLES_TABLE where resourceType == :resourceType")
    fun fetchAllArticlesFromDatabaseSingle(resourceType: ResourceType): MutableList<ArticleStorage>

    @Query("select * from ARTICLES_TABLE where url == :url")
    suspend fun fetchArticleFromDatabaseByUrl(url: String): ArticleStorage

    @Insert()
    suspend fun addNewArticleToDatabase(articleStorage: ArticleStorage)

    @Query("DELETE FROM ARTICLES_TABLE WHERE url = :url AND resourceType = :resourceType")
    suspend fun deleteArticleFromDatabaseByUrl(url: String, resourceType: ResourceType)

    @Query("DELETE FROM ARTICLES_TABLE")
    fun clearTable()
}