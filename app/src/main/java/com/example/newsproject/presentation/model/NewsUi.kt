package com.example.newsproject.presentation.model

data class NewsUi(
    val status:String,
    val totalResults:Int,
    val articles: List<ArticleUi>
)