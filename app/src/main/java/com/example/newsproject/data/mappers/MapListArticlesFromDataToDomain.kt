package com.example.newsproject.data.mappers

import com.example.newsproject.domain.models.ArticleDomain
import com.example.newsproject.data.models.ArticleData
import com.example.newsproject.domain.Mapper

class MapListArticlesFromDataToDomain(private val mapper: Mapper<ArticleData, ArticleDomain>) :
    Mapper<List<ArticleData>, List<ArticleDomain>> {
    override fun map(from: List<ArticleData>): List<ArticleDomain> = from.run {
        map(mapper::map)
    }

}