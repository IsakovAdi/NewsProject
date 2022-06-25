package com.example.newsapp.domain.models

data class Articles(
    val author:String,
    val title:String,
    val description:String,
    val publishedAt:String,
    val url:String,
    val urlToImage:String,
    val content:String,
    val source: Source
)