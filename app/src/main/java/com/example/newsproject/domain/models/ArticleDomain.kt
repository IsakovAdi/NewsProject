package com.example.newsapp.domain.models

import com.example.newsprojectj200.domain.models.SourceDomain

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