package com.example.newsproject.presentation.mappers

import com.example.newsproject.domain.models.NewsDomain
import com.example.newsproject.domain.Mapper
import com.example.newsproject.presentation.model.NewsUi

class MapNewsDomainToUi(private val mapper: MapListArticlesDomainToUi) :
    Mapper<NewsDomain, NewsUi> {
    override fun map(from: NewsDomain) = from.run {
        NewsUi(
            status = status,
            totalResults = totalResults,
            articles = mapper.map(articles)
        )
    }

}