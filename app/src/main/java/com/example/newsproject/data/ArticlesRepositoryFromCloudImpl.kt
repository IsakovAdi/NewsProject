package com.example.newsprojectj200.data

import com.example.newsapp.domain.models.ArticleDomain
import com.example.newsprojectj200.data.cache.models.ResourceType
import com.example.newsprojectj200.data.cache.source.ArticleCacheDataSource
import com.example.newsprojectj200.data.cloud.source.ArticleCloudDataSource
import com.example.newsprojectj200.data.models.ArticleData
import com.example.newsprojectj200.domain.Mapper
import com.example.newsprojectj200.domain.ArticlesRepositoryFromCloud
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

    override fun getTopHeadlines(
        keyword: String,
        country: String,
        category: String,
    ): Flow<List<ArticleDomain>> = articleCacheDataSource.fetchAllArticleFromDatabaseObservable(ResourceType.TOP_NEWS)
        .flatMapLatest {
            handleFetchTopHeadlinesArticlesFromCache(articlesFromCache = it,
                keyword = keyword,
                country = country,
                category = category)
        }.map(mapper::map)
        .flowOn(Dispatchers.Default)

    private fun handleFetchTopHeadlinesArticlesFromCache(
        articlesFromCache: List<ArticleData>,
        keyword: String,
        country: String,
        category: String,
    ): Flow<List<ArticleData>> =
        if (articlesFromCache.isEmpty()) {
            articleCloudDataSource.fetchTopHeadlinesArticlesFromCloud(
                keyword = keyword,
                country = country,
                category = category
            )
                .onEach {
                    saveArticlesToCache(it, ResourceType.TOP_NEWS)
                }
                .flowOn(Dispatchers.Default)
        } else {
            flow { emit(articlesFromCache) }
        }


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


    private suspend fun saveArticlesToCache(
        articles: List<ArticleData>,
        resourceType: ResourceType,
    ) {
        articles.forEach {
            articleCacheDataSource.addNewArticleToDatabase(it, resourceType = resourceType)
        }
    }
}