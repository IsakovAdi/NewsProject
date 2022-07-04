package com.example.newsapp.domain.usecases

import com.example.newsapp.data.Utils
import com.example.newsapp.domain.Repository
import com.example.newsapp.domain.models.News
import retrofit2.Response

class GetAllNewsUseCase(private val repository: Repository) {
    suspend fun execute(
        keyword: String,
        sortBy:String,
        language: String
    ): Response<News> {
        val source = "bbc.com , euronews.com , edition.cnn.com , news.google.com , aljazeera.com"
        return repository.getAllNews(keyword, sortBy, language, source, Utils.API_KEY)
    }
}