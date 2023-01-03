package com.example.newsprojectj200.data.cache.source


import com.example.newsprojectj200.data.cache.models.ResourceType
import com.example.newsprojectj200.data.models.ArticleData
import kotlinx.coroutines.flow.Flow

interface ArticleCacheDataSource {

    fun fetchAllArticleFromDatabaseObservable(resourceType: ResourceType): Flow<List<ArticleData>>

    fun fetchAllArticleFromDatabaseSingle(resourceType: ResourceType): List<ArticleData>

    suspend fun fetchArticleFromDatabaseByUrl(articleUrl: String): ArticleData

    suspend fun deleteArticleFromDatabaseByUrl(articleUrl: String, resourceType: ResourceType)

    suspend fun addNewArticleToDatabase(articleData: ArticleData, resourceType: ResourceType)
}