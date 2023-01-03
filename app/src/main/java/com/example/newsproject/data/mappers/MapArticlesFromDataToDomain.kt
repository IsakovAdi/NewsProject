package com.example.newsprojectj200.data.mappers

import com.example.newsapp.domain.models.ArticleDomain
import com.example.newsprojectj200.data.cloud.models.ArticleCloud
import com.example.newsprojectj200.data.models.ArticleData
import com.example.newsprojectj200.domain.Mapper

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