package com.example.newsprojectj200.data.cloud.provide

import retrofit2.Converter

interface ProvideConverterFactory {
    fun converterFactory():Converter.Factory
}