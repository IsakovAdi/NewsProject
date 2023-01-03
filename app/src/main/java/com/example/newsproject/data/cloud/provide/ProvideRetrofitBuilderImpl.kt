package com.example.newsprojectj200.data.cloud.provide

import retrofit2.Retrofit

class ProvideRetrofitBuilderImpl(
    private val provideConverterFactory: ProvideConverterFactory,
    private val provideOkHttpClientBuilder: ProvideOkHttpClientBuilder,
) : ProvideRetrofitBuilder {
    override fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()
        .addConverterFactory(provideConverterFactory.converterFactory())
        .client(provideOkHttpClientBuilder.httpClientBuilder())
}