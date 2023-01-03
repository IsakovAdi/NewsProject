package com.example.newsapp.domain.models

data class NewsDomain(
    val status:String,
    val totalResults:Int,
    val articles: List<ArticleDomain>
)