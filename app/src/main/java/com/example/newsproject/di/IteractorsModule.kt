package com.example.newsproject.di

import com.example.newsproject.domain.ArticlesRepositoryFromCache
import com.example.newsproject.domain.iteractors.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class IteractorsModule {

    @Provides
    fun deleteSavedArticleFromCache(repository: ArticlesRepositoryFromCache): DeleteArticleUseCase =
        DeleteArticleUseCaseImpl(repository = repository)

    @Provides
    fun getAllSavedArticle(repository: ArticlesRepositoryFromCache): GetAllSavedArticlesUseCase =
        GetAllSavedArticlesUseCaseImpl(repository = repository)

    @Provides
    fun saveArticleToCache(repository: ArticlesRepositoryFromCache): SaveArticleUseCase =
        SaveArticleUseCaseImpl(repository = repository)

}