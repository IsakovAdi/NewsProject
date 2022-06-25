package com.example.newsapp.domain.usecases

import com.example.newsapp.domain.Repository
import com.example.newsapp.domain.models.News
import retrofit2.Response

class GetAllNewsUseCase(private val repository: Repository) {
    suspend fun execute(
        keyword: String,
        language: String,
        apiKey: String
    ):Response<News> {
        return repository.getAllNews(keyword, language, apiKey)
    }
}