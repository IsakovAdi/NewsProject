package com.example.newsprojectj200.data.cloud.mappers

import com.example.newsapp.domain.models.ArticleDomain
import com.example.newsprojectj200.data.cloud.models.ArticleCloud
import com.example.newsprojectj200.data.models.ArticleData
import com.example.newsprojectj200.domain.Mapper

class MapListArticlesCloudToData(private val mapper: MapArticlesFromCloudToData) :
    Mapper<List<ArticleCloud>, List<ArticleData>> {
    override fun map(from: List<ArticleCloud>) = from.run {
        map(mapper::map)
    }
}