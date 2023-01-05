package com.example.newsproject.data.cloud.provide

import okhttp3.OkHttpClient

interface ProvideOkHttpClientBuilder {
    fun httpClientBuilder(): OkHttpClient
}