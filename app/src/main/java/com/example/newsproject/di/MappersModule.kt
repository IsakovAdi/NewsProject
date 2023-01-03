package com.example.newsprojectj200.di

import com.example.newsapp.domain.models.ArticleDomain
import com.example.newsapp.domain.models.NewsDomain
import com.example.newsapp.domain.models.ParamsDomain
import com.example.newsprojectj200.data.cache.mappers.*
import com.example.newsprojectj200.data.cache.models.ArticleStorage
import com.example.newsprojectj200.data.cache.models.SourceStorage
import com.example.newsprojectj200.data.cloud.mappers.*
import com.example.newsprojectj200.data.cloud.models.ArticleCloud
import com.example.newsprojectj200.data.cloud.models.NewsCloud
import com.example.newsprojectj200.data.cloud.models.ParamsCloud
import com.example.newsprojectj200.data.cloud.models.SourceCloud
import com.example.newsprojectj200.data.mappers.*
import com.example.newsprojectj200.data.models.ArticleData
import com.example.newsprojectj200.data.models.ParamsData
import com.example.newsprojectj200.data.models.SourceData
import com.example.newsprojectj200.domain.Mapper
import com.example.newsprojectj200.domain.models.SourceDomain
import com.example.newsprojectj200.presentation.mappers.*
import com.example.newsprojectj200.presentation.model.ArticleUi
import com.example.newsprojectj200.presentation.model.NewsUi
import com.example.newsprojectj200.presentation.model.ParamsUi
import com.example.newsprojectj200.presentation.model.SourceUi
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
    fun provideMapArticlesFromDataToDomain(mapper: MapSourceFromDataToDomain):
            Mapper<ArticleData, ArticleDomain> =
        MapArticlesFromDataToDomain(mapper = mapper)

    @Provides
    fun provideMapSourceFromDataToDomain(): Mapper<SourceData, SourceDomain> =
        MapSourceFromDataToDomain()

    @Provides
    fun provideMapArticleFromDomainToData(mapper: MapSourceFromDomainToData): Mapper<ArticleDomain, ArticleData> =
        MapArticleFromDomainToData(mapper = mapper)

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
            Mapper<ArticleDomain, ArticleUi> = MapUiArticleToDomain(mapper = mapper)

    @Provides
    fun provideMapUiSourceToDomain(): Mapper<SourceDomain, SourceUi> =
        MapUiSourceToDomain()

    @Provides
    fun provideMapListArticlesFromDataToDomain(mapper: Mapper<ArticleData, ArticleDomain>):
            Mapper<List<ArticleData>, List<ArticleDomain>> =
        MapListArticlesFromDataToDomain(mapper = mapper)
}