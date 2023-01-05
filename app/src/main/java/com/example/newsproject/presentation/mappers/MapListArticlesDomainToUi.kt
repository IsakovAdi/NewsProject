package com.example.newsproject.presentation.mappers

import com.example.newsproject.domain.models.ArticleDomain
import com.example.newsproject.domain.Mapper
import com.example.newsproject.presentation.model.ArticleUi

class MapListArticlesDomainToUi(private val mapper: MapDomainArticlesToUi) :
    Mapper<List<ArticleDomain>, List<ArticleUi>> {
    override fun map(from: List<ArticleDomain>) = from.run {
        map { mapper.map(it) }
    }
}