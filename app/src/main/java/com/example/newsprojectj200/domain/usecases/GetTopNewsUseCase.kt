package com.example.newsprojectj200.domain.usecases

import com.example.newsapp.data.Utils
import com.example.newsapp.domain.Repository
import com.example.newsapp.domain.models.News
import retrofit2.Response

class GetTopNewsUseCase(private val repository: Repository) {
    suspend fun execute(
        keyword: String,
        country: String,
        category:String,
    ):Response<News>{
//        val source = "bbc.com , euronews.com , edition.cnn.com , news.google.com , aljazeera.com"
        return repository.getTopHeadlines(keyword, country, category, Utils.API_KEY)
    }
}