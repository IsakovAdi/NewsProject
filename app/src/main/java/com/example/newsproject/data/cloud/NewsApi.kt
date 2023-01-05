package com.example.newsproject.data.cloud

import com.example.newsproject.data.cloud.Endpoints.EVERYTHING
import com.example.newsproject.data.cloud.Endpoints.TOP_HEADLINES
import com.example.newsproject.data.cloud.models.NewsCloud
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET(EVERYTHING)
    suspend fun getAllNews(
        @Query("q") keyword: String,
        @Query("domains") domains: String? = "bbc.com , euronews.com , edition.cnn.com , " +
                "news.google.com , aljazeera.com",
        @Query("sortBy") sortBy: String,
        @Query("language") language: String,
        @Query("apiKey") apiKey: String,
    ): Response<NewsCloud>

    @GET(TOP_HEADLINES)
    suspend fun getTopHeadlines(
        @Query("q") keyword: String,
        @Query("country") country:String = "ru",
        @Query("category") category: String,
        @Query("apiKey") apiKey: String,
    ): Response<NewsCloud>

}