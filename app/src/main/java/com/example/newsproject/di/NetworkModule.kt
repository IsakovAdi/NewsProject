package com.example.newsproject.di

import com.example.newsproject.data.cache.db.NewsDao
import com.example.newsproject.data.cloud.provide.*
import com.example.newsproject.data.cloud.NewsApi
import com.example.newsproject.domain.iteractors.ClearCacheUseCase
import com.example.newsproject.domain.iteractors.ClearCacheUseCaseImpl
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