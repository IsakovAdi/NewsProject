package com.example.newsproject.data.cloud.models

import com.google.gson.annotations.SerializedName

class SourceCloud(
    @SerializedName("id") val sourceId: String?,
    @SerializedName("name") val sourceName: String?,
)