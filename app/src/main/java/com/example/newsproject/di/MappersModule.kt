package com.example.newsproject.di

import com.example.newsproject.domain.models.ArticleDomain
import com.example.newsproject.domain.models.NewsDomain
import com.example.newsproject.domain.models.ParamsDomain
import com.example.newsproject.data.cache.mappers.MapArticleFromDataToStorage
import com.example.newsproject.data.cache.mappers.MapArticleFromDataToStorageImpl
import com.example.newsproject.data.cache.mappers.MapArticleFromStorageToData
import com.example.newsproject.data.cache.models.ArticleStorage
import com.example.newsproject.data.cloud.mappers.MapArticlesFromCloudToData
import com.example.newsproject.data.cloud.mappers.MapListArticlesCloudToData
import com.example.newsproject.data.cloud.mappers.MapParamsFromCloudToData
import com.example.newsproject.data.cloud.mappers.MapSourceFromCloudToData
import com.example.newsproject.data.cloud.models.ArticleCloud
import com.example.newsproject.data.cloud.models.ParamsCloud
import com.example.newsproject.data.cloud.models.SourceCloud
import com.example.newsproject.data.mappers.*
import com.example.newsproject.data.models.ArticleData
import com.example.newsproject.data.models.ParamsData
import com.example.newsproject.data.models.SourceData
import com.example.newsproject.domain.Mapper
import com.example.newsproject.domain.models.SourceDomain
import com.example.newsproject.presentation.mappers.*
import com.example.newsproject.presentation.model.ArticleUi
import com.example.newsproject.presentation.model.NewsUi
import com.example.newsproject.presentation.model.ParamsUi
import com.example.newsproject.presentation.model.SourceUi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MappersModule {
    @Provides
    fun provideMapArticleFromDataToStorage(): MapArticleFromDataToStorage =
        MapArticleFromDataToStorageImpl()

    @Provides
    fun provideMapArticleFromStorageToData(): Mapper<ArticleStorage, ArticleData> =
        MapArticleFromStorageToData()

    @Provides
    fun provideMapArticlesFromCloudToData(
        mapper: MapSourceFromCloudToData,
    ): Mapper<ArticleCloud, ArticleData> =
        MapArticlesFromCloudToData(mapper = mapper)



    @Provides
    fun provideMapListArticlesCloudToData(
        mapper: MapArticlesFromCloudToData,
    ): Mapper<List<ArticleCloud>, List<ArticleData>> =
        MapListArticlesCloudToData(mapper = mapper)

    @Provides
    fun provideMapParamsFromCloudToData(): Mapper<ParamsCloud, ParamsData> =
        MapParamsFromCloudToData()

    @Provides
    fun provideMapSourceFromCloudToData(): Mapper<SourceCloud, SourceData> =
        MapSourceFromCloudToData()

    @Provides
    fun provideMapArticleFromDomainToData(mapper: MapSourceFromDomainToData): Mapper<ArticleDomain, ArticleData> =
        MapArticleFromDomainToData(mapper = mapper)


    @Provides
    fun provideMapArticlesFromDataToDomain(mapper: MapSourceFromDataToDomain):
            Mapper<ArticleData, ArticleDomain> =
        MapArticlesFromDataToDomain(mapper = mapper)

    @Provides
    fun provideMapListArticlesFromDataToDomain(mapper: Mapper<ArticleData, ArticleDomain>):
            Mapper<List<ArticleData>, List<ArticleDomain>> =
        MapListArticlesFromDataToDomain(mapper = mapper)

    @Provides
    fun provideMapSourceFromDataToDomain(): Mapper<SourceData, SourceDomain> =
        MapSourceFromDataToDomain()

    @Provides
    fun provideMapSourceFromDomainToData(): Mapper<SourceDomain, SourceData> =
        MapSourceFromDomainToData()

    @Provides
    fun provideMapDomainArticlesToUi(mapper: MapDomainSourceToUi):
            Mapper<ArticleDomain, ArticleUi> = MapDomainArticlesToUi(mapper = mapper)

    @Provides
    fun provideMapDomainSourceToUi(): Mapper<SourceDomain, SourceUi> = MapDomainSourceToUi()


    @Provides
    fun provideMapListArticlesDomainToUi(mapper: MapDomainArticlesToUi):
            Mapper<List<ArticleDomain>, List<ArticleUi>> =
        MapListArticlesDomainToUi(mapper = mapper)

    @Provides
    fun provideMapNewsDomainToUi(mapper: MapListArticlesDomainToUi):
            Mapper<NewsDomain, NewsUi> = MapNewsDomainToUi(mapper = mapper)


    @Provides
    fun provideMapParamsDomainToUi(): Mapper<ParamsDomain, ParamsUi> = MapParamsDomainToUi()

    @Provides
    fun provideMapUiArticleToDomain(mapper: MapUiSourceToDomain):
            Mapper<ArticleUi, ArticleDomain> = MapUiArticleToDomain(mapper = mapper)

    @Provides
    fun provideMapUiSourceToDomain(): Mapper<SourceUi, SourceDomain> =
        MapUiSourceToDomain()



}