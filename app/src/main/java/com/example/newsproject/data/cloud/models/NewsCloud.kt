package com.example.newsproject.data.cloud.models

import com.google.gson.annotations.SerializedName

class NewsCloud(
    @SerializedName("status") val status: String,
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("articles") val articles: List<ArticleCloud>,
)