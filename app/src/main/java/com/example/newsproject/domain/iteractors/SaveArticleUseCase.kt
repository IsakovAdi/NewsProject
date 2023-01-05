package com.example.newsproject.domain.iteractors

import com.example.newsproject.domain.models.ArticleDomain
import com.example.newsproject.domain.ArticlesRepositoryFromCache
import javax.inject.Inject


interface SaveArticleUseCase {
    suspend operator fun invoke(article: ArticleDomain)
}

class SaveArticleUseCaseImpl (private val repository: ArticlesRepositoryFromCache) :
    SaveArticleUseCase {
    override suspend operator fun invoke(article: ArticleDomain) =
        repository.saveArticle(article = article)
}