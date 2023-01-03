package com.example.newsprojectj200.data.cloud.mappers

import com.example.newsprojectj200.data.cloud.models.ArticleCloud
import com.example.newsprojectj200.data.models.ArticleData
import com.example.newsprojectj200.domain.Mapper
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