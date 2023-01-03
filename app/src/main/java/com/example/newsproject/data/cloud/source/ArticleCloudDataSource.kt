package com.example.newsprojectj200.data.cloud.source

import com.example.newsprojectj200.data.models.ArticleData
import kotlinx.coroutines.flow.Flow

interface ArticleCloudDataSource {
    fun fetchAllArticlesFromCloud(
        keyword: String,
        sortBy: String,
        language: String,
    ): Flow<List<ArticleData>>

    fun fetchTopHeadlinesArticlesFromCloud(
        keyword: String,
        country: String,
        category: String,
    ): Flow<List<ArticleData>>
}