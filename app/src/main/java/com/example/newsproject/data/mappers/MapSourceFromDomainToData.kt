package com.example.newsprojectj200.data.mappers

import com.example.newsprojectj200.data.models.SourceData
import com.example.newsprojectj200.domain.Mapper
import com.example.newsprojectj200.domain.models.SourceDomain
import javax.inject.Inject

class MapSourceFromDomainToData @Inject constructor() : Mapper<SourceDomain, SourceData> {
    override fun map(from: SourceDomain) = from.run {
        SourceData(sourceId = sourceId, sourceName = name)
    }
}