package com.example.newsprojectj200.data.cache.mappers

import com.example.newsprojectj200.data.cache.models.ArticleStorage
import com.example.newsprojectj200.data.cache.models.ResourceType
import com.example.newsprojectj200.data.cache.models.SourceStorage
import com.example.newsprojectj200.data.cloud.models.ArticleCloud
import com.example.newsprojectj200.data.cloud.models.SourceCloud
import com.example.newsprojectj200.data.models.ArticleData
import com.example.newsprojectj200.data.models.SourceData
import com.example.newsprojectj200.domain.Mapper

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