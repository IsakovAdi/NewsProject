package com.example.newsprojectj200.presentation.model

data class NewsUi(
    val status:String,
    val totalResults:Int,
    val articles: List<ArticleUi>
)