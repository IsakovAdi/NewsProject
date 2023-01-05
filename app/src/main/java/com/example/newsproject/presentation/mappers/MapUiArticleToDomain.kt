package com.example.newsproject.presentation.mappers

import com.example.newsproject.domain.models.ArticleDomain
import com.example.newsproject.domain.Mapper
import com.example.newsproject.presentation.model.ArticleUi

class MapUiArticleToDomain(private val mapper: MapUiSourceToDomain) :
    Mapper<ArticleUi, ArticleDomain> {
    override fun map(from: ArticleUi) = from.run {
        ArticleDomain(
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