package com.example.newsprojectj200.domain.iteractors

import com.example.newsapp.domain.models.ArticleDomain
import com.example.newsprojectj200.domain.ArticlesRepositoryFromCache
import kotlinx.coroutines.flow.Flow

interface GetAllSavedArticlesUseCase {
    operator fun invoke(): Flow<List<ArticleDomain>>
}

class GetAllSavedArticlesUseCaseImpl(private val repository: ArticlesRepositoryFromCache) :
    GetAllSavedArticlesUseCase {
    override fun invoke() = repository.getAllSavedArticles()
}