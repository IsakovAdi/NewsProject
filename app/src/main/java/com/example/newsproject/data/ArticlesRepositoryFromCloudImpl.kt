package com.example.newsproject.data

import com.example.newsproject.domain.models.ArticleDomain
import com.example.newsproject.data.cache.models.ResourceType
import com.example.newsproject.data.cache.source.ArticleCacheDataSource
import com.example.newsproject.data.cloud.source.ArticleCloudDataSource
import com.example.newsproject.data.models.ArticleData
import com.example.newsproject.domain.Mapper
import com.example.newsproject.domain.ArticlesRepositoryFromCloud
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class ArticlesRepositoryFromCloudImpl(
    private val articleCloudDataSource: ArticleCloudDataSource,
    private val articleCacheDataSource: ArticleCacheDataSource,
    private val mapper: Mapper<List<ArticleData>, List<ArticleDomain>>,
) : ArticlesRepositoryFromCloud {

    override fun getAllNews(
        keyword: String,
        sortBy: String,
        language: String,
    ) = flow {
        emit(articleCacheDataSource.fetchAllArticleFromDatabaseSingle(ResourceType.ALL_NEWS))
    }.flatMapLatest {
        handleFetchAllArticlesFromCache(articlesFromCache = it,
            keyword = keyword,
            sortBy = sortBy,
            language = language)
    }.map(mapper::map)
        .flowOn(Dispatchers.Default)

    private fun handleFetchAllArticlesFromCache(
        articlesFromCache: List<ArticleData>,
        keyword: String,
        sortBy: String,
        language: String,
    ): Flow<List<ArticleData>> =
        if (articlesFromCache.isEmpty()) {
            articleCloudDataSource.fetchAllArticlesFromCloud(
                keyword = keyword,
                sortBy = sortBy,
                language = language)
                .onEach {
                    saveArticlesToCache(it, ResourceType.ALL_NEWS)
                }
                .flowOn(Dispatchers.Default)
        } else articleCacheDataSource.fetchAllArticleFromDatabaseObservable(resourceType = ResourceType.ALL_NEWS)

    override fun getTopHeadlines(
        keyword: String,
        category: String,
    ): Flow<List<ArticleDomain>> = articleCacheDataSource.fetchAllArticleFromDatabaseObservable(
        ResourceType.TOP_NEWS)
        .flatMapLatest {
            handleFetchTopHeadlinesArticlesFromCache(articlesFromCache = it,
                keyword = keyword,
                category = category)
        }.map(mapper::map)
        .flowOn(Dispatchers.Default)

    private fun handleFetchTopHeadlinesArticlesFromCache(
        articlesFromCache: List<ArticleData>,
        keyword: String,
        category: String,
    ): Flow<List<ArticleData>> =
        if (articlesFromCache.isEmpty()) {
            articleCloudDataSource.fetchTopHeadlinesArticlesFromCloud(
                keyword = keyword,
                category = category
            )
                .onEach {
                    saveArticlesToCache(it, ResourceType.TOP_NEWS)
                }
                .flowOn(Dispatchers.Default)
        } else {
            flow { emit(articlesFromCache) }
        }





    private suspend fun saveArticlesToCache(
        articles: List<ArticleData>,
        resourceType: ResourceType,
    ) {
        articles.forEach {
            articleCacheDataSource.addNewArticleToDatabase(it, resourceType = resourceType)
        }
    }
}