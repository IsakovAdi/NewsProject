package com.example.newsprojectj200.presentation.mappers

import com.example.newsapp.domain.models.ArticleDomain
import com.example.newsprojectj200.domain.Mapper
import com.example.newsprojectj200.presentation.model.ArticleUi

class MapListArticlesDomainToUi(private val mapper: MapDomainArticlesToUi) :
    Mapper<List<ArticleDomain>, List<ArticleUi>> {
    override fun map(from: List<ArticleDomain>) = from.run {
        map { mapper.map(it) }
    }
}