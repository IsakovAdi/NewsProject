package com.example.newsproject.data.mappers

import com.example.newsproject.data.models.SourceData
import com.example.newsproject.domain.Mapper
import com.example.newsproject.domain.models.SourceDomain
import javax.inject.Inject

class MapSourceFromDataToDomain @Inject constructor() : Mapper<SourceData, SourceDomain> {
    override fun map(from: SourceData) = from.run {
        SourceDomain(sourceId = sourceId, name = sourceName)
    }
}