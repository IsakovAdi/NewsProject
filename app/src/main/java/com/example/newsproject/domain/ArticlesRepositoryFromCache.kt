package com.example.newsprojectj200.domain

import com.example.newsapp.domain.models.ArticleDomain
import kotlinx.coroutines.flow.Flow

interface ArticlesRepositoryFromCache {
    fun getAllSavedArticles(): Flow<List<ArticleDomain>>

    suspend fun deleteSavedArticle(articleUrl: String)

    suspend fun saveArticle(article: ArticleDomain)
}