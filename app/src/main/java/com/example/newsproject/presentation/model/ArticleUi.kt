package com.example.newsproject.presentation.model

data class ArticleUi(
    val author:String?,
    val title:String?,
    val description:String?,
    val publishedAt:String?,
    val url:String?,
    val urlToImage:String?,
    val content:String?,
    val source: SourceUi?
)