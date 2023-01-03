package com.example.newsprojectj200.domain.iteractors

import com.example.newsapp.domain.models.ArticleDomain
import com.example.newsprojectj200.domain.ArticlesRepositoryFromCache


interface SaveArticleUseCase {
    suspend operator fun invoke(article: ArticleDomain)
}

class SaveArticleUseCaseImpl(private val repository: ArticlesRepositoryFromCache) {
    suspend  operator fun invoke(article: ArticleDomain) = repository.saveArticle(article = article)
}