package com.example.newsapp.domain

import com.example.newsapp.data.Api
import com.example.newsapp.data.RetrofitInstance
import com.example.newsapp.domain.models.News
import retrofit2.Response

class Repository : Api {
    override suspend fun getAllNews(
        keyword: String,
        language: String,
        apiKey: String
    ): Response<News> {
        return RetrofitInstance.api.getAllNews(keyword, language, apiKey)
    }

    override suspend fun getTopHeadlines(
        country: String,
        category: String,
        apiKey: String
    ): Response<News> {
        return RetrofitInstance.api.getTopHeadlines(country, category, apiKey)
    }

}