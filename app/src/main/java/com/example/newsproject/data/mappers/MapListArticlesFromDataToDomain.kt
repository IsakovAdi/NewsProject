package com.example.newsprojectj200.data.mappers

import com.example.newsapp.domain.models.ArticleDomain
import com.example.newsprojectj200.data.models.ArticleData
import com.example.newsprojectj200.domain.Mapper

class MapListArticlesFromDataToDomain(private val mapper: Mapper<ArticleData, ArticleDomain>) :
    Mapper<List<ArticleData>, List<ArticleDomain>> {
    override fun map(from: List<ArticleData>): List<ArticleDomain> = from.run {
        map(mapper::map)
    }

}