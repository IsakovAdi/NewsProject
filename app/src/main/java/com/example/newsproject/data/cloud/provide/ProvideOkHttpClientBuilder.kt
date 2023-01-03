package com.example.newsprojectj200.data.cloud.provide

import okhttp3.OkHttpClient

interface ProvideOkHttpClientBuilder {
    fun httpClientBuilder(): OkHttpClient
}