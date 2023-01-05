package com.example.newsproject.data.mappers

import com.example.newsproject.data.models.SourceData
import com.example.newsproject.domain.Mapper
import com.example.newsproject.domain.models.SourceDomain
import javax.inject.Inject

class MapSourceFromDomainToData @Inject constructor() : Mapper<SourceDomain, SourceData> {
    override fun map(from: SourceDomain) = from.run {
        SourceData(sourceId = sourceId, sourceName = name)
    }
}