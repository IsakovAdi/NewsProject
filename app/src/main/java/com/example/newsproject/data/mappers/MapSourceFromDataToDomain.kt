package com.example.newsprojectj200.data.mappers

import com.example.newsprojectj200.data.models.SourceData
import com.example.newsprojectj200.domain.Mapper
import com.example.newsprojectj200.domain.models.SourceDomain
import javax.inject.Inject

class MapSourceFromDataToDomain @Inject constructor() : Mapper<SourceData, SourceDomain> {
    override fun map(from: SourceData) = from.run {
        SourceDomain(sourceId = sourceId, name = sourceName)
    }
}