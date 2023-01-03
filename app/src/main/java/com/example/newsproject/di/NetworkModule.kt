package com.example.newsprojectj200.di

import com.example.newsprojectj200.data.cache.db.NewsDao
import com.example.newsprojectj200.data.cloud.NewsApi
import com.example.newsprojectj200.data.cloud.provide.*
import com.example.newsprojectj200.domain.ClearCacheUseCase
import com.example.newsprojectj200.domain.ClearCacheUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideMakeService(
        retrofitBuilder: ProvideRetrofitBuilder,
    ): MakeService = MakeServiceImpl(retrofitBuilder = retrofitBuilder)

    @Provides
    fun provideProductService(
        makeService: MakeService,
    ): NewsApi = makeService.service(NewsApi::class.java)

    @Provides
    fun provideProvideConverterFactory(): ProvideConverterFactory = ProvideConverterFactoryImpl()

    @Provides
    fun provideProvideInterceptorDebug(): ProvideInterceptor = ProvideInterceptorImpl.Debug()

    @Provides
    fun provideProvideOkHttpClientBuilder(
        provideInterceptor: ProvideInterceptor,
    ): ProvideOkHttpClientBuilder =
        ProvideOkHttpClientBuilderImpl(provideInterceptor = provideInterceptor)

    @Provides
    fun provideProvideRetrofitBuilder(
        provideConverterFactory: ProvideConverterFactory,
        provideOkHttpClientBuilder: ProvideOkHttpClientBuilder,
    ): ProvideRetrofitBuilder = ProvideRetrofitBuilderImpl(
        provideConverterFactory = provideConverterFactory,
        provideOkHttpClientBuilder = provideOkHttpClientBuilder
    )

    @Provides
    fun provideClearCacheUseCase(
        dao: NewsDao,
    ): ClearCacheUseCase = ClearCacheUseCaseImpl(dao = dao)


}