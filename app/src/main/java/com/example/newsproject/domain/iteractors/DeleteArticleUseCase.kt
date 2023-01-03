package com.example.newsprojectj200.domain.iteractors

import com.example.newsapp.domain.models.ArticleDomain
import com.example.newsprojectj200.domain.ArticlesRepositoryFromCache

interface DeleteArticleUseCase {
    suspend operator fun invoke(articleUrl: String)
}

class DeleteArticleUseCaseImpl(private val repository: ArticlesRepositoryFromCache) :
    DeleteArticleUseCase {
    override suspend fun invoke(articleUrl: String) =
        repository.deleteSavedArticle(articleUrl = articleUrl)

}
