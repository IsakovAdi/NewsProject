package com.example.newsapp.domain.models

data class News(
    val status:String,
    val totalResults:Int,
    val articles: List<Articles>
)