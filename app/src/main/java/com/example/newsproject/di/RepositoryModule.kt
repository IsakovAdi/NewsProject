package com.example.newsprojectj200.di

import android.content.Context
import com.example.newsapp.domain.models.ArticleDomain
import com.example.newsprojectj200.data.ArticlesRepositoryFromCacheImpl
import com.example.newsprojectj200.data.ArticlesRepositoryFromCloudImpl
import com.example.newsprojectj200.data.cache.source.ArticleCacheDataSource
import com.example.newsprojectj200.data.cloud.base.ResourceProvider
import com.example.newsprojectj200.data.cloud.source.ArticleCloudDataSource
import com.example.newsprojectj200.data.models.ArticleData
import com.example.newsprojectj200.domain.ArticlesRepositoryFromCache
import com.example.newsprojectj200.domain.ArticlesRepositoryFromCloud
import com.example.newsprojectj200.domain.Mapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)

class RepositoryModule {
    @Provides
    fun provideArticlesRepositoryFromCloud(
        articleCloudDataSource: ArticleCloudDataSource,
        articleCacheDataSource: ArticleCacheDataSource,
        mapper: Mapper<List<ArticleData>, List<ArticleDomain>>,
    ): ArticlesRepositoryFromCloud =
        ArticlesRepositoryFromCloudImpl(
            articleCloudDataSource = articleCloudDataSource,
            articleCacheDataSource = articleCacheDataSource,
            mapper = mapper)

    @Provides
    fun provideResourceProvider(
        @ApplicationContext context: Context,
    ): ResourceProvider = ResourceProvider.Base(context = context)


    @Provides
    fun provideArticleRepositoryFromCache(
        articleCacheDataSource: ArticleCacheDataSource,
        listMapper: Mapper<List<ArticleData>, List<ArticleDomain>>,
        articleMapper: Mapper<ArticleDomain, ArticleData>,
    ): ArticlesRepositoryFromCache =
        ArticlesRepositoryFromCacheImpl(
            articleCacheDataSource = articleCacheDataSource,
            listMapper = listMapper,
            articleMapper = articleMapper
        )


}