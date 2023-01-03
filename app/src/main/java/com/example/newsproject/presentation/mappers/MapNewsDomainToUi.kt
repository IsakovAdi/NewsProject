package com.example.newsprojectj200.presentation.mappers

import com.example.newsapp.domain.models.NewsDomain
import com.example.newsprojectj200.domain.Mapper
import com.example.newsprojectj200.presentation.model.NewsUi

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