package com.example.newsproject.domain.iteractors

import com.example.newsproject.domain.models.ArticleDomain
import com.example.newsproject.domain.ArticlesRepositoryFromCache
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetAllSavedArticlesUseCase {
    operator fun invoke(): Flow<List<ArticleDomain>>
}

class GetAllSavedArticlesUseCaseImpl (private val repository: ArticlesRepositoryFromCache) :
    GetAllSavedArticlesUseCase {
    override fun invoke() = repository.getAllSavedArticles()
}