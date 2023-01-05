package com.example.newsproject.data.cache.mappers

import com.example.newsproject.data.cache.models.ArticleStorage
import com.example.newsproject.data.cache.models.ResourceType
import com.example.newsproject.data.cache.models.SourceStorage
import com.example.newsproject.data.models.ArticleData
import com.example.newsproject.data.models.SourceData

interface MapArticleFromDataToStorage {
    fun map(from: ArticleData, resourceType: ResourceType): ArticleStorage
}

class MapArticleFromDataToStorageImpl : MapArticleFromDataToStorage {
    override fun map(from: ArticleData, resourceType: ResourceType) = from.run {
        ArticleStorage(
            author = author,
            title = title,
            description = description,
            publishedAt = publishedAt,
            url = newsUrl,
            urlToImage = urlToImage,
            content = content,
            source = source?.let(::mapSourceDataToSourceStorage),
            resourceType = resourceType
        )
    }

    private fun mapSourceDataToSourceStorage(source: SourceData) =
        SourceStorage(name = source.sourceName, sourceId = source.sourceId)
}