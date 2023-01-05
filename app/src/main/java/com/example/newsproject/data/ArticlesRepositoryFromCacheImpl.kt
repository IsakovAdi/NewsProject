package com.example.newsproject.data

import com.example.newsproject.data.cache.models.ResourceType
import com.example.newsproject.data.cache.source.ArticleCacheDataSource
import com.example.newsproject.data.models.ArticleData
import com.example.newsproject.domain.ArticlesRepositoryFromCache
import com.example.newsproject.domain.Mapper
import com.example.newsproject.domain.models.ArticleDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ArticlesRepositoryFromCacheImpl(
    private val articleCacheDataSource: ArticleCacheDataSource,
    private val listMapper: Mapper<List<ArticleData>, List<ArticleDomain>>,
    private val articleMapper: Mapper<ArticleDomain, ArticleData>,
) : ArticlesRepositoryFromCache {
    override fun getAllSavedArticles() = flow {
        emit(articleCacheDataSource.fetchAllArticleFromDatabaseSingle(ResourceType.SAVED))
    }.map(listMapper::map).flowOn(Dispatchers.Default)

    override suspend fun deleteSavedArticle(articleUrl: String) =
        withContext(Dispatchers.Default) {
            articleCacheDataSource.deleteArticleFromDatabaseByUrl(articleUrl = articleUrl,
                ResourceType.SAVED)
        }

    override suspend fun saveArticle(article: ArticleDomain) =
        withContext(Dispatchers.Default) {
            articleCacheDataSource.addNewArticleToDatabase(articleData = articleMapper.map(article),
                ResourceType.SAVED)
        }
}