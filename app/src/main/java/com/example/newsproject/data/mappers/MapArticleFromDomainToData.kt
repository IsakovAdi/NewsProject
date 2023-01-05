package com.example.newsproject.data.mappers

import com.example.newsproject.domain.models.ArticleDomain
import com.example.newsproject.data.models.ArticleData
import com.example.newsproject.domain.Mapper

class MapArticleFromDomainToData(private val mapper: MapSourceFromDomainToData) :
    Mapper<ArticleDomain, ArticleData> {
    override fun map(from: ArticleDomain) = from.run {
        ArticleData(
            author = author,
            title = title,
            description = description,
            content = content,
            newsUrl = url,
            urlToImage = urlToImage,
            publishedAt = publishedAt,
            source = source?.let {
                mapper.map(it)
            }
        )
    }

}