package com.example.newsprojectj200.presentation.mappers

import com.example.newsapp.domain.models.ArticleDomain
import com.example.newsprojectj200.domain.Mapper
import com.example.newsprojectj200.presentation.model.ArticleUi
import javax.inject.Inject

class MapDomainArticlesToUi @Inject constructor(private val mapper: MapDomainSourceToUi) :
    Mapper<ArticleDomain, ArticleUi> {
    override fun map(from: ArticleDomain) = from.run {
        ArticleUi(
            author = author,
            title = title,
            description = description,
            publishedAt = publishedAt,
            url = url,
            urlToImage = urlToImage,
            content = content,
            source = source?.let {
                mapper.map(it)
            }
        )
    }
}