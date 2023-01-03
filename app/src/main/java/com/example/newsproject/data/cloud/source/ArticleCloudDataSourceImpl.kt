package com.example.newsprojectj200.data.cloud.source

import com.example.newsprojectj200.data.cloud.NewsApi
import com.example.newsprojectj200.data.cloud.Utils
import com.example.newsprojectj200.data.cloud.models.ArticleCloud
import com.example.newsprojectj200.data.models.ArticleData
import com.example.newsprojectj200.domain.Mapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class ArticleCloudDataSourceImpl(
    private val api: NewsApi,
    private val mapper: Mapper<List<ArticleCloud>, List<ArticleData>>,
) : ArticleCloudDataSource {
    override fun fetchAllArticlesFromCloud(
        keyword: String,
        sortBy: String,
        language: String,
    ): Flow<List<ArticleData>> = flow {
        emit(
            api.getAllNews(
                keyword = keyword,
                sortBy = sortBy,
                language = language,
                apiKey = Utils.API_KEY))
    }.flowOn(Dispatchers.IO)
        .map { it.body()!! }
        .map { it.articles }
        .map(mapper::map)
        .flowOn(Dispatchers.Default)

    override fun fetchTopHeadlinesArticlesFromCloud(
        keyword: String,
        country: String,
        category: String,
    ): Flow<List<ArticleData>> = flow {
        emit(
            api.getTopHeadlines(
                keyword = keyword,
                country = country,
                category = category,
                apiKey = Utils.API_KEY))
    }.flowOn(Dispatchers.IO)
        .map { it.body()!! }
        .map { it.articles }
        .map(mapper::map)
        .flowOn(Dispatchers.Default)


}