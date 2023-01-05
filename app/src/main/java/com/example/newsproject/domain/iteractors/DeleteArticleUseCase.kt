package com.example.newsproject.domain.iteractors

import com.example.newsproject.domain.ArticlesRepositoryFromCache

interface DeleteArticleUseCase {
    suspend operator fun invoke(articleUrl: String)
}

class DeleteArticleUseCaseImpl (private val repository: ArticlesRepositoryFromCache) :
    DeleteArticleUseCase {
    override suspend fun invoke(articleUrl: String) =
        repository.deleteSavedArticle(articleUrl = articleUrl)

}
