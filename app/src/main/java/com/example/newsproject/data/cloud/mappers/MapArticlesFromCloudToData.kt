package com.example.newsproject.data.cloud.mappers

import com.example.newsproject.data.cloud.models.ArticleCloud
import com.example.newsproject.data.models.ArticleData
import com.example.newsproject.domain.Mapper
import javax.inject.Inject

class MapArticlesFromCloudToData @Inject constructor(private val mapper: MapSourceFromCloudToData) :
    Mapper<ArticleCloud, ArticleData> {
    override fun map(from: ArticleCloud) = from.run {
        ArticleData(
            author = author,
            title = title,
            description = description,
            content = content,
            newsUrl = newsUrl,
            urlToImage = urlToImage,
            publishedAt = publishedAt,
            source = source?.let(mapper::map)
        )
    }
}