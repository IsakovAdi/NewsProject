package com.example.newsprojectj200.data.cache.mappers

import com.example.newsprojectj200.data.cache.models.ArticleStorage
import com.example.newsprojectj200.data.cache.models.SourceStorage
import com.example.newsprojectj200.data.cloud.models.ArticleCloud
import com.example.newsprojectj200.data.cloud.models.SourceCloud
import com.example.newsprojectj200.data.models.ArticleData
import com.example.newsprojectj200.data.models.SourceData
import com.example.newsprojectj200.domain.Mapper

class MapArticleFromStorageToData : Mapper<ArticleStorage, ArticleData> {
    override fun map(from: ArticleStorage) = from.run {
        ArticleData(
            author = author,
            title = title,
            description = description,
            publishedAt = publishedAt,
            newsUrl = url,
            urlToImage = urlToImage,
            content = content,
            source = source?.let(::mapSourceStorageToSourceData)
        )
    }

    private fun mapSourceStorageToSourceData(source: SourceStorage) =
        SourceData(sourceName = source.name, sourceId = source.sourceId)
}