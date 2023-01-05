package com.example.newsproject.data.cloud.provide

import retrofit2.Converter

interface ProvideConverterFactory {
    fun converterFactory():Converter.Factory
}