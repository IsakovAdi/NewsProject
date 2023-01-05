package com.example.newsproject.data.models

import com.google.gson.annotations.SerializedName

class SourceData(
    @SerializedName("id") val sourceId: String?,
    @SerializedName("name") val sourceName: String?,
)