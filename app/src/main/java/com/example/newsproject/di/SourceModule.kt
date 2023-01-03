package com.example.newsprojectj200.di

import com.example.newsprojectj200.data.cache.db.NewsDao
import com.example.newsprojectj200.data.cache.mappers.MapArticleFromDataToStorage
import com.example.newsprojectj200.data.cache.models.ArticleStorage
import com.example.newsprojectj200.data.cache.source.ArticleCacheDataSource
import com.example.newsprojectj200.data.cache.source.ArticleCacheDataSourceImpl
import com.example.newsprojectj200.data.cloud.NewsApi
import com.example.newsprojectj200.data.cloud.models.ArticleCloud
import com.example.newsprojectj200.data.cloud.source.ArticleCloudDataSource
import com.example.newsprojectj200.data.cloud.source.ArticleCloudDataSourceImpl
import com.example.newsprojectj200.data.models.ArticleData
import com.example.newsprojectj200.domain.Mapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SourceModule {
    @Provides
    fun provideArticleCacheDataSource(
        newsDao: NewsDao,
        mapArticleFromCacheToData: Mapper<ArticleStorage, ArticleData>,
        mapArticleFromDataToCache: MapArticleFromDataToStorage,
    ): ArticleCacheDataSource = ArticleCacheDataSourceImpl(
        newsDao = newsDao,
        mapArticleFromCacheToData = mapArticleFromCacheToData,
        mapArticleFromDataToCache = mapArticleFromDataToCache)

    @Provides
    fun provideArticleCloudDataSource(
        api: NewsApi,
        mapper: Mapper<List<ArticleCloud>, List<ArticleData>>,
    ): ArticleCloudDataSource =
        ArticleCloudDataSourceImpl(
            api = api,
            mapper = mapper
        )

//    @Provides
//    fun provide

//    @Provides
//    fun provide

//    @Provides
//    fun provide

//    @Provides
//    fun provide

//    @Provides
//    fun provide
}