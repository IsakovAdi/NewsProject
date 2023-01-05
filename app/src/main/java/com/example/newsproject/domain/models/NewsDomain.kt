package com.example.newsproject.domain.models

data class NewsDomain(
    val status:String,
    val totalResults:Int,
    val articles: List<ArticleDomain>
)