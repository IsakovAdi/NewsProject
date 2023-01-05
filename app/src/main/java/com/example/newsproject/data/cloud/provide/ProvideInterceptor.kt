package com.example.newsproject.data.cloud.provide

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

interface ProvideInterceptor {
    fun loggingInterceptor(): HttpLoggingInterceptor

    fun requestInterceptor(): Interceptor
}