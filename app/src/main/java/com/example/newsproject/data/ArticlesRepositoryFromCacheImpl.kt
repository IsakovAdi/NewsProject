package com.example.newsprojectj200.data

import androidx.lifecycle.Transformations.map
import com.example.newsapp.domain.models.ArticleDomain
import com.example.newsprojectj200.data.cache.models.ResourceType
import com.example.newsprojectj200.data.cache.source.ArticleCacheDataSource
import com.example.newsprojectj200.data.models.ArticleData
import com.example.newsprojectj200.domain.ArticlesRepositoryFromCache
import com.example.newsprojectj200.domain.Mapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
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