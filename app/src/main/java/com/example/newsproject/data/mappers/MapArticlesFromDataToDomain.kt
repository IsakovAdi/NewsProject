package com.example.newsproject.data.mappers

import com.example.newsproject.domain.models.ArticleDomain
import com.example.newsproject.data.models.ArticleData
import com.example.newsproject.domain.Mapper

class MapArticlesFromDataToDomain(private val mapper: MapSourceFromDataToDomain) :
    Mapper<ArticleData, ArticleDomain> {
    override fun map(from: ArticleData) = from.run {
        ArticleDomain(
            author = author,
            title = title,
            description = description,
            content = content,
            url = newsUrl,
            urlToImage = urlToImage,
            publishedAt = publishedAt,
            source = source?.let {
                mapper.map(it)
            }
        )
    }
}