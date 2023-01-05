package com.example.newsproject.data.cloud.mappers

import com.example.newsproject.data.cloud.models.ArticleCloud
import com.example.newsproject.data.models.ArticleData
import com.example.newsproject.domain.Mapper

class MapListArticlesCloudToData(private val mapper: MapArticlesFromCloudToData) :
    Mapper<List<ArticleCloud>, List<ArticleData>> {
    override fun map(from: List<ArticleCloud>) = from.run {
        map(mapper::map)
    }
}