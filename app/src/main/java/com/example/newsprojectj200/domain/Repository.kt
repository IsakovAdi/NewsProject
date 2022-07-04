package com.example.newsapp.domain

import com.example.newsapp.data.Api
import com.example.newsapp.data.RetrofitInstance
import com.example.newsapp.domain.models.Params
import com.example.newsapp.domain.models.News
import retrofit2.Response

interface Repository{
    suspend fun getAllNews(
        keyword: String,
        sortBy:String,
        language: String,
        domains: String,
        apiKey: String
    )    : Response<News>
    suspend fun getTopHeadlines(
        keyword: String,
        country: String,
        category: String,
        apiKey: String
    ):Response<News>
    fun getCategories(): List<Params>
    fun getSortBy(): List<Params>
    fun getAllLanguage(): List<Params>
    fun getAllCountries():List<Params>


//
}