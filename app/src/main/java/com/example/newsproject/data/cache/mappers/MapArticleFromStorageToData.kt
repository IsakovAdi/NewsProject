package com.example.newsproject.data.cache.mappers

import com.example.newsproject.data.cache.models.ArticleStorage
import com.example.newsproject.data.cache.models.SourceStorage
import com.example.newsproject.data.models.ArticleData
import com.example.newsproject.data.models.SourceData
import com.example.newsproject.domain.Mapper

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