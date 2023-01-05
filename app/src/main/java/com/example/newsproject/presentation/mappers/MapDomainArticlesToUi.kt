package com.example.newsproject.presentation.mappers

import com.example.newsproject.domain.models.ArticleDomain
import com.example.newsproject.domain.Mapper
import com.example.newsproject.presentation.model.ArticleUi
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