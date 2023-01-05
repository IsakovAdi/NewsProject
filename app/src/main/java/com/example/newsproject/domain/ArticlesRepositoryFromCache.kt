package com.example.newsproject.domain

import com.example.newsproject.domain.models.ArticleDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ArticlesRepositoryFromCache {
    fun getAllSavedArticles(): Flow<List<ArticleDomain>>

    suspend fun deleteSavedArticle(articleUrl: String)

    suspend fun saveArticle(article: ArticleDomain)
}