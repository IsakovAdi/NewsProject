package com.example.newsproject.data.cloud.mappers

import com.example.newsproject.data.cloud.models.SourceCloud
import com.example.newsproject.data.models.SourceData
import com.example.newsproject.domain.Mapper
import javax.inject.Inject

class MapSourceFromCloudToData @Inject constructor() : Mapper<SourceCloud, SourceData> {
    override fun map(from: SourceCloud) = from.run {
        SourceData(sourceId = sourceId, sourceName = sourceName)
    }
}