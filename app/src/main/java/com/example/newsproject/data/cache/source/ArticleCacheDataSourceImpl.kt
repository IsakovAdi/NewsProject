package com.example.newsprojectj200.data.cache.source

import com.example.newsprojectj200.data.cache.db.NewsDao
import com.example.newsprojectj200.data.cache.mappers.MapArticleFromDataToStorage
import com.example.newsprojectj200.data.cache.models.ArticleStorage
import com.example.newsprojectj200.data.cache.models.ResourceType
import com.example.newsprojectj200.data.models.ArticleData
import com.example.newsprojectj200.domain.Mapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ArticleCacheDataSourceImpl @Inject constructor(
    private val newsDao: NewsDao,
    private val mapArticleFromCacheToData: Mapper<ArticleStorage, ArticleData>,
    private val mapArticleFromDataToCache: MapArticleFromDataToStorage,
) : ArticleCacheDataSource {

    override suspend fun addNewArticleToDatabase(
        articleData: ArticleData,
        resourceType: ResourceType,
    ) =
        newsDao.addNewArticleToDatabase(mapArticleFromDataToCache.map(from = articleData,
            resourceType = resourceType))

    override fun fetchAllArticleFromDatabaseObservable(resourceType: ResourceType): Flow<List<ArticleData>> =
        newsDao.fetchAllArticlesFromDatabaseObservable(resourceType = resourceType)
            .debounce(300)
            .flowOn(Dispatchers.IO)
            .map { articles -> articles.map(mapArticleFromCacheToData::map) }
            .flowOn(Dispatchers.Default)

    override fun fetchAllArticleFromDatabaseSingle(resourceType: ResourceType): List<ArticleData> =
        newsDao.fetchAllArticlesFromDatabaseSingle(resourceType = resourceType)
            .map(mapArticleFromCacheToData::map)


    override suspend fun fetchArticleFromDatabaseByUrl(articleUrl: String): ArticleData =
        mapArticleFromCacheToData.map(newsDao.fetchArticleFromDatabaseByUrl(articleUrl))

    override suspend fun deleteArticleFromDatabaseByUrl(
        articleUrl: String,
        resourceType: ResourceType,
    ) =
        newsDao.deleteArticleFromDatabaseByUrl(url = articleUrl, resourceType = resourceType)
}