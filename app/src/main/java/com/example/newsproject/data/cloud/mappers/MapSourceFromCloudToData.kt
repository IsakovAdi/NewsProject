package com.example.newsprojectj200.data.cloud.mappers

import com.example.newsprojectj200.data.cloud.models.SourceCloud
import com.example.newsprojectj200.data.models.SourceData
import com.example.newsprojectj200.domain.Mapper
import com.example.newsprojectj200.domain.models.SourceDomain
import javax.inject.Inject

class MapSourceFromCloudToData @Inject constructor() : Mapper<SourceCloud, SourceData> {
    override fun map(from: SourceCloud) = from.run {
        SourceData(sourceId = sourceId, sourceName = sourceName)
    }
}