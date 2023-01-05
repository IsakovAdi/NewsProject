package com.example.newsproject.data.cloud.source

import com.example.newsproject.data.models.ArticleData
import kotlinx.coroutines.flow.Flow

interface ArticleCloudDataSource {
    fun fetchAllArticlesFromCloud(
        keyword: String,
        sortBy: String,
        language: String,
    ): Flow<List<ArticleData>>

    fun fetchTopHeadlinesArticlesFromCloud(
        keyword: String,
        category: String,
    ): Flow<List<ArticleData>>
}