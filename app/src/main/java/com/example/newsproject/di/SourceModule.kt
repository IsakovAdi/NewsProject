package com.example.newsproject.di

import com.example.newsproject.data.cache.db.NewsDao
import com.example.newsproject.data.cache.mappers.MapArticleFromDataToStorage
import com.example.newsproject.data.cache.models.ArticleStorage
import com.example.newsproject.data.cache.source.ArticleCacheDataSource
import com.example.newsproject.data.cache.source.ArticleCacheDataSourceImpl
import com.example.newsproject.data.cloud.NewsApi
import com.example.newsproject.data.cloud.models.ArticleCloud
import com.example.newsproject.data.cloud.source.ArticleCloudDataSource
import com.example.newsproject.data.cloud.source.ArticleCloudDataSourceImpl
import com.example.newsproject.data.models.ArticleData
import com.example.newsproject.domain.Mapper
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