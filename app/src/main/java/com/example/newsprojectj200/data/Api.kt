package com.example.newsapp.data

import com.example.newsapp.domain.models.News
import com.example.newsapp.domain.models.Params
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


// val name:String
// name = "Adilet"
// q = android kotlin, sport, tv, football, judo, politician
// language =
interface Api {
    @GET("everything")
    suspend fun getAllNews(
        @Query("q") keyword:String,
        @Query("sortBy") sortBy:String,
        @Query("language") language:String,
        @Query("domains") domains: String,
        @Query("apiKey") apiKey:String
    ):Response<News>

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("q") keyword: String,
        @Query("country") country:String,
        @Query("category") category:String,
        @Query("apiKey") apiKey:String
    ):Response<News>

    fun getCategory():List<Params>
    fun getSortBy():List<Params>
    fun getAllLanguage(): List<Params>
    fun getAllCountries():List<Params>

}