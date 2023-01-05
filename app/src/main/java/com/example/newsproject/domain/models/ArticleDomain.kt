package com.example.newsproject.domain.models


data class ArticleDomain(
    val author:String?,
    val title:String?,
    val description:String?,
    val publishedAt:String?,
    val url:String?,
    val urlToImage:String?,
    val content:String?,
    val source: SourceDomain?
)