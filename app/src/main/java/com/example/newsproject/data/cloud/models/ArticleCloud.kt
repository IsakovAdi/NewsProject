package com.example.newsproject.data.cloud.models

import com.google.gson.annotations.SerializedName

class ArticleCloud(
    @SerializedName("author") val author: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("publishedAt") val publishedAt: String?,
    @SerializedName("url") val newsUrl: String?,
    @SerializedName("urlToImage") val urlToImage: String?,
    @SerializedName("content") val content: String?,
    @SerializedName("source") val source: SourceCloud?,
)